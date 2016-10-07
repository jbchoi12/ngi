package egovframework.let.ngi.chg.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : TnChangeInfoRadiusSearchingVO.java
 * @Description : TnChangeInfo Default VO class
 * @Modification Information
 *
 * @author 이정연
 * @since 2014-09-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ChangeInfoRadiusSearchingVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String centerX = "";
	
	private String centerY = "";
	
	private String radius = "";
	
	/** leftRect */
    private String leftRect = "";
    
    /** righRect */
    private String rightRect = "";
    
    /** topRect */
    private String topRect = "";
    
    /** bottomRect */
    private String bottomRect = "";
    
	/** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 5;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private String processSttusSe = "";
    
    
        
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}  

	public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public String getLeftRect() {
		return leftRect;
	}

	public void setLeftRect(String leftRect) {
		this.leftRect = leftRect;
	}

	public String getTopRect() {
		return topRect;
	}

	public void setTopRect(String topRect) {
		this.topRect = topRect;
	}

	public String getBottomRect() {
		return bottomRect;
	}

	public void setBottomRect(String bottomRect) {
		this.bottomRect = bottomRect;
	}

	public String getRightRect() {
		return rightRect;
	}

	public void setRightRect(String rightRect) {
		this.rightRect = rightRect;
	}

	public String getCenterY() {
		return centerY;
	}

	public void setCenterY(String centerY) {
		this.centerY = centerY;
	}

	public String getCenterX() {
		return centerX;
	}

	public void setCenterX(String centerX) {
		this.centerX = centerX;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getProcessSttusSe() {
		return processSttusSe;
	}

	public void setProcessSttusSe(String processSttusSe) {
		this.processSttusSe = processSttusSe;
	}

}
