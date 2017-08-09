package com.ajayhao.simplelab.web.toolbox;

import com.ajayhao.simplelab.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajay.hao on 2016/11/17.
 */
@Component("allRounderTool")
public class AllRounderTool implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object execute(String beanName, String methodName, String paramTypeStr, String paramJsonStr){
        //basic check
        paramValidate(beanName);

        Object beanInstance = applicationContext.getBean(beanName);
        List<Class> methodParamClassList = prepareParamTypeClassList(paramTypeStr);
        Method method = prepareMethodInstance( methodName, beanInstance.getClass(), methodParamClassList);
        List<Object> paramObjList = prepareParamInstance(methodParamClassList, paramJsonStr);

        Object result = null;
        try {
            if(StringUtils.isNotEmpty(paramTypeStr)) {
                result = method.invoke(beanInstance, paramObjList.toArray());
            }else {
                result = method.invoke(beanInstance);
            }
        } catch (Exception e) {
             //throw new BaseException(BizCode.UNKNOWN, "调用方法"+beanName+"."+methodName+"报错:e="+e);
        }

        return result;
    }

    private List<Object> prepareParamInstance(List<Class> methodParamClassList, String paramsJsonStr) {
        if (StringUtils.isNotEmpty(paramsJsonStr)) {
            List<Object> paramObjList = new ArrayList();
            List<String> paramValList = ObjectUtils.fromJson(paramsJsonStr,List.class);
            if(methodParamClassList != null && methodParamClassList.size() == paramValList.size()) {
                for (int i = 0; i < paramValList.size(); i++) {
                    String paramStrTmp = "";
                    Object param = null;
                    if(paramValList.get(i) instanceof String){
                        paramStrTmp = (String) paramValList.get(i);
                    }else{
                        paramStrTmp = ObjectUtils.toJson( paramValList.get(i));
                    }
                    if(methodParamClassList.get(i).isAssignableFrom(String.class)){
                        param = paramStrTmp;
                    }else {
                        param = ObjectUtils.fromJson(paramStrTmp, methodParamClassList.get(i));
                    }
                    paramObjList.add(param);
                }
                return paramObjList;
            }/*else{
                throw new BaseException(BizCode.SYS_ERROR, "参数类型与参数个数不匹配");
            }*/
        }else{
            return null;
        }
        return null;
    }

    private Method prepareMethodInstance(String methodName, Class beanClass, List<Class> paramClassTypeList) {
        Method method = null;
        try {
            if (paramClassTypeList != null) {
                method = beanClass.getMethod(methodName, paramClassTypeList.toArray(new Class[1]));
            }else{
                method = beanClass.getMethod(methodName);
            }
        } catch (NoSuchMethodException e) {
            //throw new BaseException(BizCode.ParamError, "待执行的方法不存在:" + methodName);
        }
        return method;
    }

    private List<Class> prepareParamTypeClassList(String paramTypeStr){
        if (StringUtils.isNotEmpty(paramTypeStr)) {
            List<String> paramClassNameList = ObjectUtils.fromJson(paramTypeStr, List.class);
            List<Class> paramClassList = new ArrayList<>();
            for (String paramClassName : paramClassNameList) {
                try {
                    paramClassList.add(Class.forName(paramClassName));
                } catch (ClassNotFoundException e) {
                    //throw new BaseException(BizCode.ParamError, "待执行的方法参数类型有误或不存在:" + paramClassName);
                }
            }
            return paramClassList;
        }else {
            return null;
        }
    }

    private void paramValidate(String beanName) {
        if(StringUtils.isEmpty(beanName)){
            //throw new BaseException(BizCode.ParamIsEmpty, "传入的beanName为空");
        }

        if(StringUtils.isEmpty(beanName)){
            //fthrow new BaseException(BizCode.ParamIsEmpty, "传入的methodName为空");
        }
    }

}
