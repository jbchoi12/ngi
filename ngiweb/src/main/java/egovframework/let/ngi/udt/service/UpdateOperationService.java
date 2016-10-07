package egovframework.let.ngi.udt.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface UpdateOperationService {
	/**
	 * 갱신작업의 정보를 등록한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void insertUpdateOperation(UpdateOperationVO vo) throws Exception;
	
	/**
	 * 갱신작업의 정보를 수정한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void updateUpdateOpertation(UpdateOperationVO vo) throws Exception;
	
	/**
	 * 갱신작업의 정보를 삭제한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @throws Exception
	 */
	public void deleteUpdateOperation(UpdateOperationVO vo) throws Exception;

	/**
	 * 갱신작업의 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation(UpdateOperationVO vo) throws Exception;

	/**
	 * 갱신작업의 상세정보를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UpdateOperationVO selectUpdateOperation2(UpdateOperationVO vo) throws Exception;	
	
	
	/**
	 * 갱신작업의 목록을 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UpdateOperationVO> selectUpdateOperationList(UpdateOperationVO vo) throws Exception;
	
	/**
	 * 갱신작업의 목록 수를 조회한다.
	 * @author kka
	 * @since 2014. 10. 29.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectUpdateOperationListTotCnt(UpdateOperationVO vo) throws Exception;
	
	/**
	 * 도엽 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 12.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMapdmcList(MapdmcVO vo) throws Exception;
	
	/**
	 * 도엽번호 목록을 조회한다.
	 * @author kka
	 * @since 2014. 11. 12.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMapdmcDetailList(MapdmcVO vo) throws Exception;
}
