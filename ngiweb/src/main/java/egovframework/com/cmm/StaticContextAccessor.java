package egovframework.com.cmm;

import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Autowired로 bean을 가져오기 곤란한 static method에서 bean을 사용하기 위한 것
 * @author byungdori
 * @since 2014. 10. 29.
 * @version 1.0
 * @see 
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *	
 *  </pre>
 */
@Component
public class StaticContextAccessor implements ApplicationContextAware {
	private static StaticContextAccessor staticContext;
	private ApplicationContext applicationContext;

	@PostConstruct
	public void registerInstance() {
		staticContext = this;
	} 

	/**
	 * class타입으로 bean을 가져온다.
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return staticContext.applicationContext.getBean(clazz);
	}

	/**
	 * 이름으로 bean을 가져온다.
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return staticContext.applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}