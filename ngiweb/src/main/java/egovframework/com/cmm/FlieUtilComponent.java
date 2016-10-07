package egovframework.com.cmm;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.property.EgovPropertyService;

@Component
@Scope("singleton")
public class FlieUtilComponent {

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	public String getEaisWithFilename() {
		return propertyService.getString("eais_with_filename");	// 세움터 파일명.
	}
	
	public String getKaisWithFilename() {
		return propertyService.getString("kais_with_filename"); // 새주소 파일명.
	}
	
	public String getEaisDirectoryPath() {
		// 세움터 경로.
		return propertyService.getString("eais_dir");
	}
	
	public String getKaisDirectoryPath() {
		// 새주소 경로.
		return propertyService.getString("kais_dir");
	}
	
	// 세움터.
	public List getEaisFileList(int firstIndex, int lastIndex) {
		
		List<FlieUtilComponentVO> resultList = new ArrayList<FlieUtilComponentVO>();
		
		// 파일 리스트.
        File file = new File(getEaisDirectoryPath());
		File[] files = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith(getEaisWithFilename());	 
			}
		});
       
		//정렬.
        Arrays.sort( files, new Comparator<File>() {
            public int compare( File a, File b ) {
                // do your comparison here returning -1 if a is before b, 0 if same, 1 if a is after b
            	Long aFile = new Long(a.lastModified());
            	Long bFile = new Long(b.lastModified());
            	return bFile.compareTo(aFile);
            }
        } );
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String fileName;
    	String filePath;
    	String fileModDate;
    	
    	FlieUtilComponentVO vo;
    	
        /*for(int i=0;i<files.length;i++) {
        	
        	fileName = files[i].getName();
        	filePath = files[i].getAbsolutePath();
        	fileModDate = sdf.format(files[i].lastModified());
        	
        	//System.out.println(fileName + " " + fileModDate + " " + filePath);
        	vo = new FlieUtilComponentVO();
        	vo.setFileName(fileName);
        	vo.setFilePath(filePath);
        	vo.setFileModDate(fileModDate);
        	vo.setTypeDesc("eais");
        	
        	if( firstIndex <= i && lastIndex > i )
        		resultList.add(vo);
        }*/
        
        for(int i=0;i<30;i++) {
        	
        	fileName = "테스트파일 : " + i;
        	filePath = "";
        	fileModDate = "2016-08-"+i;
        	
        	//System.out.println(fileName + " " + fileModDate + " " + filePath);
        	vo = new FlieUtilComponentVO();
        	vo.setFileName(fileName);
        	vo.setFilePath(filePath);
        	vo.setFileModDate(fileModDate);
        	vo.setTypeDesc("eais");
        	
        	if( firstIndex <= i && lastIndex > i )
        		resultList.add(vo);
        }
        
        return resultList;
	}
	
	// 세움터.
	public int getEaisFileCount() {
		File file = new File(getEaisDirectoryPath());
		File[] files = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith(getEaisWithFilename());	// 확장자.
			}
		});
		return files.length;
	};
	
	
	// 새주소.
	public List getKaisFileList(int firstIndex, int lastIndex) {
			
		List<FlieUtilComponentVO> resultList = new ArrayList<FlieUtilComponentVO>();
		
		// 파일 리스트.
        File file = new File(getKaisDirectoryPath());
		File[] files = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith(getKaisWithFilename());	// 확장자.
			}
		});
       
		//정렬.
        Arrays.sort( files, new Comparator<File>() {
            public int compare( File a, File b ) {
                // do your comparison here returning -1 if a is before b, 0 if same, 1 if a is after b
            	Long aFile = new Long(a.lastModified());
            	Long bFile = new Long(b.lastModified());
            	return bFile.compareTo(aFile);
            } 
        } );
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String fileName;
    	String filePath;
    	String fileModDate;
    	FlieUtilComponentVO vo;
    	
        /*for(int i=0;i<files.length;i++) {
        	
        	fileName = files[i].getName();
        	filePath = files[i].getAbsolutePath();
        	fileModDate = sdf.format(files[i].lastModified());
        	
        	//System.out.println(fileName + " " + fileModDate + " " + filePath);
        	vo = new FlieUtilComponentVO();
        	vo.setFileName(fileName);
        	vo.setFilePath(filePath);
        	vo.setFileModDate(fileModDate);
        	vo.setTypeDesc("kais");
        	
        	if( firstIndex <= i && lastIndex > i )
        		resultList.add(vo);
        }*/
        
        for(int i=0;i<30;i++) {
        	
        	fileName = "새주소 "+i;
        	filePath = "";
        	fileModDate = "2016-08-"+i;
        	
        	//System.out.println(fileName + " " + fileModDate + " " + filePath);
        	vo = new FlieUtilComponentVO();
        	vo.setFileName(fileName);
        	vo.setFilePath(filePath);
        	vo.setFileModDate(fileModDate);
        	vo.setTypeDesc("kais");
        	
        	if( firstIndex <= i && lastIndex > i )
        		resultList.add(vo);
        }
        
        return resultList;
	}
		
	// 새주소.
	public int getKaisFileCount() {
		File file = new File(getKaisDirectoryPath());
		File[] files = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith(getKaisWithFilename());	// 확장자.
			}
		});
		return files.length;
	};
		
}
