package egovframework.com.cmm.util;

import java.util.StringTokenizer;

public class EncodeUtil {

	  //유니코드를 String 으로 바꿔주는 또는 반대로 해주는 메소드들
	 public String StrtoUni(String str){
	   String uni="";
	   for(int i=0;i<str.length();i++){
	    
	    if((i+1)%2==1){
	     char char1=str.charAt(i);
	     char char2=str.charAt(i+1);
	     char chr=(char) (char1-char2);
	     String hex=Integer.toHexString(chr);
	     uni+="\\u"+hex;
	    }
	      
	   }
	   return uni;
	  }
	  
	  public String UnitoStr(String uni){
	   
	   String str="";
	   StringTokenizer str1=new StringTokenizer(uni,"\\u");
	   
	   while(str1.hasMoreTokens()){
	    String str2=str1.nextToken();
	    int i=Integer.parseInt(str2,16);
	    str+=(char)i;
	    
	   }
	   return str;
	  }	
	
}
