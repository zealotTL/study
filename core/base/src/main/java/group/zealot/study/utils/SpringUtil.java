package group.zealot.study.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class SpringUtil {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    private ResourcePatternResolver resourcePatternResolver;
    private MetadataReaderFactory metadataReaderFactory;

    @PostConstruct
    protected void postConstruct() {
        resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    public void regist(String beanName, Object bean) {
        defaultListableBeanFactory.registerSingleton(beanName, bean);
    }

    public void autowireBean(Object bean) {
        //自动注入依赖
        beanFactory.autowireBean(bean);
    }

    /**
     * 扫描 特定包[group.zealot.king] 指定注解[annotationType]
     *
     * @return className
     */
    public Set<String> scanGetBeanNameSet(Class<? extends Annotation> annotationType) throws IOException {
        return scanGetBeanNameSet(annotationType, "classpath*:group/zealot/king/**/*.class");
    }

    /**
     * 扫描 特定包[basePackages] 指定注解[annotationType]
     *
     * @return className
     */
    public Set<String> scanGetBeanNameSet(Class<? extends Annotation> annotationType, String basePackages) throws IOException {
        logger.info("class scanGetBeanNameSet [" + annotationType.getName() + "],basePackages [" + basePackages + "]");
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(annotationType);
        Set<String> set = new HashSet<>();
        Resource[] resources = resourcePatternResolver.getResources(basePackages);
        for (Resource r : resources) {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(r);
            if (annotationTypeFilter.match(metadataReader, metadataReaderFactory)) {
                set.add(metadataReader.getClassMetadata().getClassName());
            }

        }
        if (set.isEmpty()) {
            logger.warn("No class was found in '" + basePackages + "' package. ");
        }
        return set;
    }

    /**
     * 扫描 特定包[basePackages]
     *
     * @return className
     */
    public Set<String> scanGetBeanNameSet(String basePackages) throws IOException {
        logger.info("class scanGetBeanNameSet basePackages [" + basePackages + "]");
        Set<String> set = new HashSet<>();
        Resource[] resources = resourcePatternResolver.getResources(basePackages);
        for (Resource r : resources) {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(r);
            set.add(metadataReader.getClassMetadata().getClassName());
        }
        if (set.isEmpty()) {
            logger.warn("No class was found in '" + basePackages + "' package. ");
        }
        return set;
    }

    /**
     * 扫描 特定包[group.zealot.king] 指定注解[annotationType]
     *
     * @return classBean
     */
    public Set<Object> scanGetBeanSet(Class<? extends Annotation> annotationType) throws IOException {
        return getBeanSet(scanGetBeanNameSet(annotationType));
    }

    /**
     * 扫描 特定包[basePackages] 指定注解[annotationType]
     *
     * @return classBean
     */
    public Set<Object> scanGetBeanSet(Class<? extends Annotation> annotationType, String basePackages) throws IOException {
        return getBeanSet(scanGetBeanNameSet(annotationType, basePackages));
    }

    /**
     * 扫描 特定包[group.zealot.king] 指定注解[annotationType]
     *
     * @return className:classBean
     */
    public Map<String, Object> scanGetBeanMap(Class<? extends Annotation> annotationType) throws IOException {
        return getBeanMap(scanGetBeanNameSet(annotationType));
    }

    /**
     * 扫描 特定包[basePackages]
     *
     * @return className:classBean
     */
    public Map<String, Object> scanGetBeanMap(String basePackages) throws IOException {
        return getBeanMap(scanGetBeanNameSet(basePackages));
    }

    /**
     * 扫描 特定包[basePackages] 指定注解[annotationType]
     *
     * @return className:classBean
     */
    public Map<String, Object> scanGetBeanMap(Class<? extends Annotation> annotationType, String basePackages) throws IOException {
        return getBeanMap(scanGetBeanNameSet(annotationType, basePackages));
    }

    /**
     * 通过className,到spring容器[SpringUtil.getApplicationContext()]中获取classBean
     *
     * @return className:classBean
     */
    public Map<String, Object> getBeanMap(Set<String> set) {
        HashMap<String, Object> beanMap = new HashMap<>();
        if (applicationContext == null) {
            throw new RuntimeException("null point of applicationContext");
        } else {
            for (String name : set) {
//                String beanName = name.substring(name.lastIndexOf(".") + 1);
                Object bean;
                try {
                    bean = applicationContext.getBean(Class.forName(name));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                beanMap.put(name, bean);
            }

        }
        if (beanMap.isEmpty()) {
            logger.warn("No Bean was found in scanGetBeanNameSet set.");
        }
        return beanMap;
    }

    /**
     * 通过className,到spring容器[SpringUtil.getApplicationContext()]中获取classBean
     *
     * @return classBean
     */
    public Set<Object> getBeanSet(Set<String> set) {
        HashSet<Object> beanSet = new HashSet<>();
        if (applicationContext == null) {
            throw new RuntimeException("null point of applicationContext");
        } else {
            for (String name : set) {
//                String beanName = name.substring(name.lastIndexOf(".") + 1);
                Object bean;
                try {
                    bean = applicationContext.getBean(Class.forName(name));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                beanSet.add(bean);
            }

        }
        if (beanSet.isEmpty()) {
            logger.warn("No Bean was found in scanGetBeanNameSet set.");
        }
        return beanSet;
    }
}
