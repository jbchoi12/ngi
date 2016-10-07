package egovframework.com.cmm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.geotools.data.DataUtilities;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WMSUtils;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.request.GetMapRequest;
import org.geotools.data.wms.response.GetMapResponse;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.map.FeatureLayer;
import org.geotools.map.MapContent;
import org.geotools.map.MapViewport;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.ExternalGraphic;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;


public class MapToSaveImage {
	
	private static GeometryFactory geometryFactory = null;
	private static CRSAuthorityFactory   factory = null;
	private static CoordinateReferenceSystem crs = null;
	private static MapContent mapContent = null;
	private static MapViewport viewport = null;
	private StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
	private FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
	
	public MapToSaveImage() {
		System.setProperty("org.geotools.referencing.forceXY", "true");
		geometryFactory = JTSFactoryFinder.getGeometryFactory( null );
		factory = CRS.getAuthorityFactory(true);
		try {
			crs = factory.createCoordinateReferenceSystem("EPSG:5179");
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapContent = new MapContent();
		viewport = mapContent.getViewport();
	}

	private double[] getGCfromLL(double lon, double lat) {
		double[] res = new double[2];
		res[0] = 0;
		res[1] = 1;
		double dx = lon * 4000000.0  / 180;
		double dy = Math.log(Math.tan((90 + lat) * Math.PI / 360))/(Math.PI / 180);
		dy = dy * 4000000.0  / 180;
		res[0] = dx;
		res[1] = dy;
		return res;
	}
	
	/**
	 *  이미지 저장.
	 * @param vworldURL : basemap url
	 * @param pX : map center lon
	 * @param pY : map center lat
	 * @param pZ : map zoom level
	 * @param pW : map width
	 * @param pH : map height
	 * @param bbox : map bbox
	 * @param wmsURL : wms server url
	 * @param layers : 레이어명
	 * @param vectors : 벡터정보
	 * @return outputBase64encode (base64 encoded)
	 */
	public String saveMapToImage(String vworldURL, String pX, String pY, String pZ, String pW, String pH, String bbox,
			String wmsURL, ArrayList<String> layers, ArrayList<String> vectors) {
		
		String outputBase64encode = null;
		
		double TILE_SIZE 	= 256.0;
		
		double _x	= Double.parseDouble(pX);
		double _y 	= Double.parseDouble(pY);
		int    _z 	= Integer.parseInt(pZ);
		int    _w 	= Integer.parseInt(pW);
		int    _h 	= Integer.parseInt(pH);

//		double[] arrRes = new double[12];
//			arrRes[0] = 793.7515875031751;
//			arrRes[1] = 338.6673440013547;
//			arrRes[2] = 169.33367200067735;
//			arrRes[3] = 84.66683600033868;
//			arrRes[4] = 42.33341800016934;
//			arrRes[5] = 21.16670900008467;
//			arrRes[6] = 10.583354500042335;
//			arrRes[7] = 5.291677250021167;
//			arrRes[8] = 2.6458386250105836;
//			arrRes[9] = 1.3229193125052918;
//			arrRes[10] = 0.6614596562526459;
//			arrRes[11] = 0.33072982812632296;
			
		double[] arrRes = new double[20];
			arrRes[0] = 125094.23288798578;
			arrRes[1] = 62547.11644399289;
			arrRes[2] = 31273.558221996445;
			arrRes[3] = 15636.779110998223;
			arrRes[4] = 7818.389555499111;
			arrRes[5] = 3909.1947777495557;
			arrRes[6] = 1954.5973888747778;
			arrRes[7] =  977.2986944373889;
			arrRes[8] =  488.64934721869446;
			arrRes[9] = 244.32467360934723;
			arrRes[10] = 122.16233680467361;
			arrRes[11] = 61.08116840233681;
			arrRes[12] = 30.540584201168404;
			arrRes[13] = 15.270292100584202;
			arrRes[14] = 7.635146050292101;
			arrRes[15] = 3.8175730251460505;
			arrRes[16] = 1.9087865125730252;
			arrRes[17] = 0.9543932562865126;
			arrRes[18] = 0.4771966281432563;
			arrRes[19] = 0.23859831407162815;

            
				
		int tileSize = 256;
		// 타일 인덱스 계산 TMS index
		int zoom   = _z;
		double lon = _x;
		double lat = _y;
		
//		double[] ll = getGCfromLL(lon, lat);
//		lon = ll[0];
//		lat = ll[1];
		
		double res = arrRes[zoom];
		double maxExtentLeft = -200000.0 ;
		double maxExtentTop = 4000000.0 ;
//		double maxExtentTop = 7853900;

		double fx = Math.round((lon - maxExtentLeft) / (res * tileSize));
		double fy = Math.round((maxExtentTop - lat) / (res * tileSize));

		int col = (int)Math.floor(fx);
		int row = (int)Math.floor(fy);
		//int z = zoom;
		
		// 타일 이미지의 sx, sy 계산
		int sx = (int)Math.floor((lon - (col * (res * tileSize) + maxExtentLeft))/res);
		int sy = (int)Math.floor((maxExtentTop - (row * (res * tileSize)) - lat)/res);

		// 타일 크기 설정
		int mapWidth = (int)Math.floor(_w/2);
		int mapHeight = (int)Math.floor(_h/2);
		
		// 640x640 이미지를 위한 가져올 타일 계산
		int bx = (int)Math.ceil((mapWidth - sx)/TILE_SIZE);
		int by = (int)Math.ceil((mapHeight - sy)/TILE_SIZE);
		int ex = (int)Math.ceil((sx + mapWidth)/TILE_SIZE);
		int ey = (int)Math.ceil((sy + mapHeight)/TILE_SIZE);
		
		int wCnt = (bx+ex);
		int hCnt = (by+ey);
		BufferedImage mergedImage = new BufferedImage(wCnt*tileSize, hCnt*tileSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D)mergedImage.getGraphics();
		graphics.setColor(Color.WHITE);  
		graphics.fillRect(0, 0, mergedImage.getWidth(), mergedImage.getHeight());  
		
		int iCnt=0;
		int jCnt=0;
		for(int next_col=(row-by) ; next_col<(row+ey) ; next_col++ ) {
			iCnt=0;
			for(int next_row=(col-bx) ; next_row<(col+ex) ;next_row++) {
				try {
					//String vworldTileURL = vworldURL +"/"+ String.valueOf(z)+"/"+String.valueOf(j)+"/"+String.valueOf(i);//+".png";
					String vworldTileURL = vworldURL +"/"+ String.valueOf(zoom)+"/"+String.valueOf(next_col)+"/"+String.valueOf(next_row); //+".png";
					BufferedImage img = fileUrlReadAndDownload(vworldTileURL);
					graphics.drawImage(img, iCnt*tileSize, jCnt*tileSize, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				iCnt++;
			}
			jCnt++;
		}
		
		
		try {
			int startX = (int) ((TILE_SIZE*bx)+sx-mapWidth);
			int startY = (int) ((TILE_SIZE*by)+sy-mapHeight);
			
			BufferedImage mergedImage2 = mergedImage.getSubimage(startX, startY, _w, _h);
			
			// wms 레이어 처리.
			if( layers.get(0).length()>0 ) {
				Graphics2D graphics2 = (Graphics2D)mergedImage2.getGraphics();
				BufferedImage wmsImage = fetchWmsImage(wmsURL, layers, bbox, pW, pH);
				graphics2.drawImage(wmsImage, 0, 0, null);
			}
			
			// 벡터 처리.
			Graphics2D graphics3 = (Graphics2D)mergedImage2.getGraphics();
			BufferedImage vectorImage = vecToImg(pW, pH, bbox, vectors);
			graphics3.drawImage(vectorImage, 0, 0, null);
//			if( vectors.get(0).length()>0 ) {
//				Graphics2D graphics3 = (Graphics2D)mergedImage2.getGraphics();
//				BufferedImage vectorImage = fetchVectorImage(vectors, bbox, pW, pH);
//				graphics3.drawImage(vectorImage, 0, 0, null);
//			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputStream b64 = new net.iharder.Base64.OutputStream(out);
			ImageIO.write(mergedImage2, "png", b64);	// 파일 쓰기.
			
			outputBase64encode = out.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputBase64encode;
	}
	
	/**
	 * wms에서 레이어 이미지 생성.
	 * @param wmsURL
	 * @param layerNames
	 * @param bbox
	 * @param width
	 * @param height
	 * @return BufferedImage
	 */
	private BufferedImage fetchWmsImage(String wmsURL, ArrayList<String> layerNames, String bbox, String width, String height) {
		
	    URL url = null;
	    BufferedImage image = null;
//	    MapContent map = new MapContent();
//        map.setTitle("saveMap");
	    WebMapServer wms = null;
	    try {
	    	url = new URL(wmsURL);
	        wms = new WebMapServer(url);
	
	        WMSCapabilities capabilities = wms.getCapabilities();
	        GetMapRequest request = wms.createGetMapRequest();
	
	        request.setFormat("image/png");
	        request.setDimensions(width, height);
	        request.setTransparent(true);
	        request.setSRS("EPSG:5179");
	        request.setBBox(bbox);
	        request.setProperty("isBaseLayer", "false");
	        request.setProperty("opacity", "1.0");
	        
	        for(String layerAndStyleName: layerNames) {
	        	
	        	String layerName = "" + layerAndStyleName.split(",")[0]; 
	        	String styleName = null;
	        	
	        	if( layerAndStyleName.split(",").length > 1 ) {
	        		styleName = layerAndStyleName.split(",")[1].equals("null") ? null : layerAndStyleName.split(",")[1] ; // 스타일명.
	        	} 
	        	
	        	for (Layer layerObj : WMSUtils.getNamedLayers(capabilities)) {
	        		if(layerObj.getName().equals(layerName)) {
	        			
	        			if( styleName == null )
	        				request.addLayer(layerObj);
	        			else 
	        				request.addLayer(layerObj, styleName);
	        			
	        		}
	        	}
	        }
	        GetMapResponse response = (GetMapResponse) wms.issueRequest(request);
	        image = ImageIO.read(response.getInputStream());
	    } catch (MalformedURLException e) {
	    	e.printStackTrace();
	    } catch (ServiceException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    }
	    return image;
	}

	/**
	 * 타일맵 주소 다운.
	 * @param fileAddress
	 * @return BufferedImage
	 */
	private BufferedImage fileUrlReadAndDownload(String fileAddress) {
		URLConnection uCon = null;
		InputStream is = null;
		BufferedImage imageBuf = null;
		try {
			URL Url = new URL(fileAddress);
			uCon = Url.openConnection();
			is = uCon.getInputStream();
			imageBuf = ImageIO.read(is);
		} catch (Exception e) {
			//e.printStackTrace();
			//System.err.println(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				//e.printStackTrace();
				//System.err.println(e.getMessage());
			}
		}
		return imageBuf;
	}
	
	/**
	 * 벡터 생성.
	 * @return BufferedImage
	 */
	public BufferedImage vecToImg(String _width, String _height, String _bbox, ArrayList<String> vectors) throws Exception {
		
		viewport.setCoordinateReferenceSystem(crs);
		String []bbox = _bbox.split(",");
		int width = Integer.parseInt(_width);
		int height = Integer.parseInt(_height);
		
		JMapFrame frame = new JMapFrame(mapContent);
        frame.setSize(width, height);
        //frame.enableStatusBar(true);
        //frame.enableToolBar(true);
        //frame.setVisible(true);
        
        WKTReader2 wktReader = new WKTReader2();
		for( String wkt : vectors ) {
			if(wkt.length()>0) {
				Geometry geom = wktReader.read(wkt);
				String geometryType = geom.getGeometryType();
				if(geometryType.equals("LineString")) {
					LineString line = (LineString) wktReader.read(geom.toString());
					createLineString(line);
				} else if(geometryType.equals("Polygon")) {
					Polygon polygon = (Polygon) wktReader.read(geom.toString());
					createPolygon(polygon);
				} else if(geometryType.equals("Point")) {
					Point point = (Point) wktReader.read(geom.toString());
					createPoint(point);
				} else {
					
				}
			}
		}
        viewport = mapContent.getViewport();
	    viewport.setCoordinateReferenceSystem(crs);
	    
	    CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:5179");
	    double x1 = Double.parseDouble(bbox[0]);
	    double x2 = Double.parseDouble(bbox[2]);
		double y1 = Double.parseDouble(bbox[1]);
		double y2 = Double.parseDouble(bbox[3]);
		ReferencedEnvelope set_bbox = new ReferencedEnvelope(x1, x2, y1, y2, targetCRS);
	    viewport.setBounds( set_bbox );
	    
	    BufferedImage image = saveImage(mapContent, set_bbox, width, height);
	    
	    mapContent.dispose();
	    frame.dispose();
	    
	    return image;
	}
	
	private BufferedImage saveImage(MapContent map, ReferencedEnvelope bbox, int imageWidth, int imageHeight) {
		
	    GTRenderer renderer = new StreamingRenderer();
	    renderer.setMapContent(map);
	    Rectangle imageBounds = new Rectangle(0, 0, imageWidth, imageHeight);

	    BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = image.createGraphics();
//	    graphics.fill(imageBounds);
	    //renderer.paint(gr, imageBounds, mapBounds);
		renderer.paint(graphics, imageBounds, bbox);
//	        File fileToSave = new File(file);
//	        ImageIO.write(image, "png", fileToSave);
		return image;
	}
	

	private void createLineString(LineString line) throws SchemaException {
//		System.out.println("createLineString");
        SimpleFeatureType TYPE = DataUtilities.createType("location","geom:LineString,name:String");
		DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal",TYPE);
//		Style style = SLD.createLineStyle(Color.decode("0xE41536"), 2f);
		featureCollection.add( SimpleFeatureBuilder.build( TYPE, new Object[]{ line, "temp"}, null) );
		org.geotools.map.Layer layer = new FeatureLayer(featureCollection, getStyleLine());
		layer.setVisible(true);
		mapContent.addLayer(layer); 
	}
	
	private void createPoint(Point point) throws SchemaException {
//		System.out.println("createPoint");
		SimpleFeatureType TYPE = DataUtilities.createType("location","geom:Point,name:String");
		DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal",TYPE);
//		Style style = SLD.createPointStyle("Circle", Color.BLUE, Color.YELLOW, 1f, 2f);
		featureCollection.add( SimpleFeatureBuilder.build( TYPE, new Object[]{ point, "temp"}, null) );
		org.geotools.map.Layer layer = new FeatureLayer(featureCollection, getStylePoint());
		layer.setVisible(true);
		mapContent.addLayer(layer); 
	}

	private void createPolygon(Polygon polygon) throws SchemaException {
//		System.out.println("createPolygon");
		SimpleFeatureType TYPE = DataUtilities.createType("location","geom:Polygon,name:String");
		DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal",TYPE);
		//Style style = SLD.createPolygonStyle(Color.decode("0x214DD8"), Color.decode("0x214DD8"), 0.2f);
		featureCollection.add( SimpleFeatureBuilder.build( TYPE, new Object[]{ polygon, "temp"}, null) );
		org.geotools.map.Layer layer = new FeatureLayer(featureCollection, getStylePolygon() );
		layer.setVisible(true);
		mapContent.addLayer(layer); 
	}
	
	private Style getStylePolygon() {
        // create a partially opaque outline stroke
        Stroke stroke = styleFactory.createStroke( filterFactory.literal( Color.decode("0x214DD8") ), filterFactory.literal(2), filterFactory.literal(0.6));
        // create a partial opaque fill
        Fill fill = styleFactory.createFill( filterFactory.literal( Color.decode("0x214DD8") ), filterFactory.literal(0.2) );
        PolygonSymbolizer sym = styleFactory.createPolygonSymbolizer(stroke, fill, null);
        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);
        
        return style;
	}
	
	private Style getStylePoint() {
		StyleBuilder styleBuilder = new StyleBuilder();
		Graphic graphic = styleBuilder.createGraphic();
		URL resource = MapToSaveImage.class.getResource("marker_test.png");
		ExternalGraphic external = styleBuilder.createExternalGraphic(resource.toString(), "image/png");
		graphic.graphicalSymbols().add(external);
		graphic.graphicalSymbols().add(styleBuilder.createMark("circle"));
        PointSymbolizer sym = styleFactory.createPointSymbolizer(graphic, null);
        
        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
	}
	
	private Style getStyleLine() {
        Stroke stroke = styleFactory.createStroke( filterFactory.literal(Color.decode("0xE41536")), filterFactory.literal(2), filterFactory.literal(0.6));
        LineSymbolizer sym = styleFactory.createLineSymbolizer(stroke, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }


}
