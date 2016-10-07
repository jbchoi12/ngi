/* Copyright (c) 2006-2013 by OpenLayers Contributors (see authors.txt for
 * full list of contributors). Published under the 2-clause BSD license.
 * See license.txt in the OpenLayers distribution or repository for the
 * full text of the license. */

/**
 * @requires OpenLayers/Popup/Framed.js
 * @requires OpenLayers/Util.js
 * @requires OpenLayers/BaseTypes/Bounds.js
 * @requires OpenLayers/BaseTypes/Pixel.js
 * @requires OpenLayers/BaseTypes/Size.js
 */

/**
 * Class: OpenLayers.Popup.FramedCloud
 * 
 * Inherits from: 
 *  - <OpenLayers.Popup.Framed>
 */
OpenLayers.Popup.FramedCloud = 
  OpenLayers.Class(OpenLayers.Popup.Framed, {

    /** 
     * Property: contentDisplayClass
     * {String} The CSS class of the popup content div.
     */
    contentDisplayClass: "olFramedCloudPopupContent",

    /**
     * APIProperty: autoSize
     * {Boolean} Framed Cloud is autosizing by default.
     */
    autoSize: true,

    /**
     * APIProperty: panMapIfOutOfView
     * {Boolean} Framed Cloud does pan into view by default.
     */
    panMapIfOutOfView: true,

    /**
     * APIProperty: imageSize
     * {<OpenLayers.Size>}
     */
    imageSize: new OpenLayers.Size(676, 736),
//    imageSize: new OpenLayers.Size(1276, 736),

    /**
     * APIProperty: isAlphaImage
     * {Boolean} The FramedCloud does not use an alpha image (in honor of the 
     *     good ie6 folk out there)
     */
    isAlphaImage: false,

    /** 
     * APIProperty: fixedRelativePosition
     * {Boolean} The Framed Cloud popup works in just one fixed position.
     */
    fixedRelativePosition: false,

    /**
     * Property: positionBlocks
     * {Object} Hash of differen position blocks, keyed by relativePosition
     *     two-character code string (ie "tl", "tr", "bl", "br")
     */
    /*
    positionBlocks: {
        "tl": {
            'offset': new OpenLayers.Pixel(44, 0),
            'padding': new OpenLayers.Bounds(8, 40, 8, 9),
            'blocks': [
                { // top-left
                    size: new OpenLayers.Size('auto', 'auto'),
                    anchor: new OpenLayers.Bounds(0, 51, 22, 0),
                    position: new OpenLayers.Pixel(0, 0)
                },
                { //top-right
                    size: new OpenLayers.Size(22, 'auto'),
                    anchor: new OpenLayers.Bounds(null, 50, 0, 0),
                    position: new OpenLayers.Pixel(-1238, 0)
                },
                { //bottom-left
                    size: new OpenLayers.Size('auto', 19),
                    anchor: new OpenLayers.Bounds(0, 32, 22, null),
                    position: new OpenLayers.Pixel(0, -631)
                },
                { //bottom-right
                    size: new OpenLayers.Size(22, 18),
                    anchor: new OpenLayers.Bounds(null, 32, 0, null),
                    position: new OpenLayers.Pixel(-1238, -632)
                },
                { // stem
                    size: new OpenLayers.Size(81, 35),
                    anchor: new OpenLayers.Bounds(null, 0, 0, null),
                    position: new OpenLayers.Pixel(0, -688)
                }
            ]
        },
        "tr": {
            'offset': new OpenLayers.Pixel(-45, 0),
            'padding': new OpenLayers.Bounds(8, 40, 8, 9),
            'blocks': [
                { // top-left
                    size: new OpenLayers.Size('auto', 'auto'),
                    anchor: new OpenLayers.Bounds(0, 51, 22, 0),
                    position: new OpenLayers.Pixel(0, 0)
                },
                { //top-right
                    size: new OpenLayers.Size(22, 'auto'),
                    anchor: new OpenLayers.Bounds(null, 50, 0, 0),
                    position: new OpenLayers.Pixel(-1238, 0)
                },
                { //bottom-left
                    size: new OpenLayers.Size('auto', 19),
                    anchor: new OpenLayers.Bounds(0, 32, 22, null),
                    position: new OpenLayers.Pixel(0, -631)
                },
                { //bottom-right
                    size: new OpenLayers.Size(22, 19),
                    anchor: new OpenLayers.Bounds(null, 32, 0, null),
                    position: new OpenLayers.Pixel(-1238, -631)
                },
                { // stem
                    size: new OpenLayers.Size(81, 35),
                    anchor: new OpenLayers.Bounds(0, 0, null, null),
                    position: new OpenLayers.Pixel(-215, -687)
                }
            ]
        },
        "bl": {
            'offset': new OpenLayers.Pixel(45, 0),
            'padding': new OpenLayers.Bounds(8, 9, 8, 40),
            'blocks': [
                { // top-left
                    size: new OpenLayers.Size('auto', 'auto'),
                    anchor: new OpenLayers.Bounds(0, 21, 22, 32),
                    position: new OpenLayers.Pixel(0, 0)
                },
                { //top-right
                    size: new OpenLayers.Size(22, 'auto'),
                    anchor: new OpenLayers.Bounds(null, 21, 0, 32),
                    position: new OpenLayers.Pixel(-1238, 0)
                },
                { //bottom-left
                    size: new OpenLayers.Size('auto', 21),
                    anchor: new OpenLayers.Bounds(0, 0, 22, null),
                    position: new OpenLayers.Pixel(0, -629)
                },
                { //bottom-right
                    size: new OpenLayers.Size(22, 21),
                    anchor: new OpenLayers.Bounds(null, 0, 0, null),
                    position: new OpenLayers.Pixel(-1238, -629)
                },
                { // stem
                    size: new OpenLayers.Size(81, 33),
                    anchor: new OpenLayers.Bounds(null, null, 0, 0),
                    position: new OpenLayers.Pixel(-101, -674)
                }
            ]
        },
        "br": {
            'offset': new OpenLayers.Pixel(-44, 0),
            'padding': new OpenLayers.Bounds(8, 9, 8, 40),
            'blocks': [
                { // top-left
                    size: new OpenLayers.Size('auto', 'auto'),
                    anchor: new OpenLayers.Bounds(0, 21, 22, 32),
                    position: new OpenLayers.Pixel(0, 0)
                },
                { //top-right
                    size: new OpenLayers.Size(22, 'auto'),
                    anchor: new OpenLayers.Bounds(null, 21, 0, 32),
                    position: new OpenLayers.Pixel(-1238, 0)
                },
                { //bottom-left
                    size: new OpenLayers.Size('auto', 21),
                    anchor: new OpenLayers.Bounds(0, 0, 22, null),
                    position: new OpenLayers.Pixel(0, -629)
                },
                { //bottom-right
                    size: new OpenLayers.Size(22, 21),
                    anchor: new OpenLayers.Bounds(null, 0, 0, null),
                    position: new OpenLayers.Pixel(-1238, -629)
                },
                { // stem
                    size: new OpenLayers.Size(81, 33),
                    anchor: new OpenLayers.Bounds(0, null, null, 0),
                    position: new OpenLayers.Pixel(-311, -674)
                }
            ]
        }
    },
    */
    positionBlocks : {
		"tl" : {
			'offset' : new OpenLayers.Pixel(44, 27),
			'padding' : new OpenLayers.Bounds(8, 60, 20, 9),
			'blocks' : [ { // top-left
				size : new OpenLayers.Size('auto', 'auto'),
				anchor : new OpenLayers.Bounds(0, 72, 39, 0),
				position : new OpenLayers.Pixel(0, 0)
			}, { // top-right
				size : new OpenLayers.Size(39, 'auto'),
				anchor : new OpenLayers.Bounds(null, 72, 0, 0),
				position : new OpenLayers.Pixel(-621, 0)
			}, { // bottom-left
				size : new OpenLayers.Size('auto', 38),
				anchor : new OpenLayers.Bounds(0, 34, 39, null),
				position : new OpenLayers.Pixel(0, -611)
			}, { // bottom-right
				size : new OpenLayers.Size(39, 38),
				anchor : new OpenLayers.Bounds(null, 34, 0, null),
				position : new OpenLayers.Pixel(-621, -611)
			}, { // stem
				size : new OpenLayers.Size(81, 54),
				anchor : new OpenLayers.Bounds(null, 0, 0, null),
				position : new OpenLayers.Pixel(0, -685)
			} ]
		},
		"tr" : {
			'offset' : new OpenLayers.Pixel(-40, 27),
			'padding' : new OpenLayers.Bounds(8, 60, 20, 9),
			'blocks' : [ { // top-left
				size : new OpenLayers.Size('auto', 'auto'),
				anchor : new OpenLayers.Bounds(0, 72, 39, 0),
				position : new OpenLayers.Pixel(0, 0)
			}, { // top-right
				size : new OpenLayers.Size(39, 'auto'),
				anchor : new OpenLayers.Bounds(null, 72, 0, 0),
				position : new OpenLayers.Pixel(-621, 0)
			}, { // bottom-left
				size : new OpenLayers.Size('auto', 38),
				anchor : new OpenLayers.Bounds(0, 34, 39, null),
				position : new OpenLayers.Pixel(0, -611)
			}, { // bottom-right
				size : new OpenLayers.Size(39, 38),
				anchor : new OpenLayers.Bounds(null, 34, 0, null),
				position : new OpenLayers.Pixel(-621, -611)
			}, { // stem
				size : new OpenLayers.Size(81, 54),
				anchor : new OpenLayers.Bounds(0, 0, null, null),
				position : new OpenLayers.Pixel(-225, -685)
			} ]
		},
		"bl" : {
			'offset' : new OpenLayers.Pixel(45, -30),
			'padding' : new OpenLayers.Bounds(8, 29, 20, 40),
			'blocks' : [ { // top-left
				size : new OpenLayers.Size('auto', 'auto'),
				anchor : new OpenLayers.Bounds(0, 38, 39, 32),
				position : new OpenLayers.Pixel(0, 0)
			}, { // top-right
				size : new OpenLayers.Size(39, 'auto'),
				anchor : new OpenLayers.Bounds(null, 38, 0, 32),
				position : new OpenLayers.Pixel(-621, 0)
			}, { // bottom-left
				size : new OpenLayers.Size('auto', 55),
				anchor : new OpenLayers.Bounds(0, -17, 39, null),
				position : new OpenLayers.Pixel(0, -611)
			}, { // bottom-right
				size : new OpenLayers.Size(39, 55),
				anchor : new OpenLayers.Bounds(null, -17, 0, null),
				position : new OpenLayers.Pixel(-621, -611)
			}, { // stem
				size : new OpenLayers.Size(81, 37),
				anchor : new OpenLayers.Bounds(null, null, 0, 0),
				position : new OpenLayers.Pixel(-101, -674)
			} ]
		},
		"br" : {
			'offset' : new OpenLayers.Pixel(-27, -30),
			'padding' : new OpenLayers.Bounds(8, 29, 20, 40),
			'blocks' : [ { // top-left
				size : new OpenLayers.Size('auto', 'auto'),
				anchor : new OpenLayers.Bounds(0, 38, 39, 32),
				position : new OpenLayers.Pixel(0, 0)
			}, { // top-right
				size : new OpenLayers.Size(39, 'auto'),
				anchor : new OpenLayers.Bounds(null, 38, 0, 32),
				position : new OpenLayers.Pixel(-621, 0)
			}, { // bottom-left
				size : new OpenLayers.Size('auto', 55),
				anchor : new OpenLayers.Bounds(0, -17, 39, null),
				position : new OpenLayers.Pixel(0, -611)
			}, { // bottom-right
				size : new OpenLayers.Size(39, 55),
				anchor : new OpenLayers.Bounds(null, -17, 0, null),
				position : new OpenLayers.Pixel(-621, -611)
			}, { // stem
				size : new OpenLayers.Size(81, 37),
				anchor : new OpenLayers.Bounds(0, null, null, 0),
				position : new OpenLayers.Pixel(-328, -674)
			} ]
		}

	},

    /**
     * APIProperty: minSize
     * {<OpenLayers.Size>}
     */
    minSize: new OpenLayers.Size(105, 10),

    /**
     * APIProperty: maxSize
     * {<OpenLayers.Size>}
     */
    maxSize: new OpenLayers.Size(1200, 660),

    /** 
     * Constructor: OpenLayers.Popup.FramedCloud
     * 
     * Parameters:
     * id - {String}
     * lonlat - {<OpenLayers.LonLat>}
     * contentSize - {<OpenLayers.Size>}
     * contentHTML - {String}
     * anchor - {Object} Object to which we'll anchor the popup. Must expose 
     *     a 'size' (<OpenLayers.Size>) and 'offset' (<OpenLayers.Pixel>) 
     *     (Note that this is generally an <OpenLayers.Icon>).
     * closeBox - {Boolean}
     * closeBoxCallback - {Function} Function to be called on closeBox click.
     */
    initialize:function(id, lonlat, contentSize, contentHTML, anchor, closeBox, 
                        closeBoxCallback) {

        this.imageSrc = OpenLayers.Util.getImageLocation('cloud-popup-relative-shadow.png');
        OpenLayers.Popup.Framed.prototype.initialize.apply(this, arguments);
        this.contentDiv.className = this.contentDisplayClass;
        this.div.className = "CloudPopup";
    },

    CLASS_NAME: "OpenLayers.Popup.FramedCloud"
});
