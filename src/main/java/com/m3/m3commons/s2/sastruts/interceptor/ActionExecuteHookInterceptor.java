package com.m3.m3commons.s2.sastruts.interceptor;

import com.m3.m3commons.s2.sastruts.annotation.PostExecute;
import com.m3.m3commons.s2.sastruts.annotation.PostExecuteMethod;
import com.m3.m3commons.s2.sastruts.annotation.PreExecute;
import com.m3.m3commons.s2.sastruts.annotation.PreExecuteMethod;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.log.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Stack;

/**
 * Actionクラスの@Executeメソッドのinvoke前後をフックするインタセプタです。<br>
 * 起動したメソッドから見えるフィールドであれば値をセットできます。
 *
 * @author k-sera
 */
public class ActionExecuteHookInterceptor extends AbstractActionInterceptor {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(ActionExecuteHookInterceptor.class);

    @Override
    public Object invokeExecute(MethodInvocation invocation) throws Throwable {
        try {
            Object action = invocation.getThis();
            Class<?> actionClass = action.getClass();
            Method invokeMethod = invocation.getMethod();

            // PreExecute
            PreExecute preExecuteConfig = invokeMethod.getAnnotation(PreExecute.class);
            boolean preExecuteDefaultExecute = (preExecuteConfig != null) ? preExecuteConfig.autoExecute() : true;
            String[] preExecuteIncludes = (preExecuteConfig != null) ? preExecuteConfig.includeMethods() : new String[0];
            String[] preExecuteExcludes = (preExecuteConfig != null) ? preExecuteConfig.excludeMethods() : new String[0];
            Stack<Method> stackedPreExecuteInvocations = new Stack<Method>();
            while (actionClass != Object.class) {
                Method[] methods = actionClass.getDeclaredMethods();
                for (Method method : methods) {
                    PreExecuteMethod methodConfig = method.getAnnotation(PreExecuteMethod.class);
                    if (methodConfig != null) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            boolean isExecutable = (preExecuteDefaultExecute) ? methodConfig.autoExecute() : false;
                            // included method or not
                            if (preExecuteIncludes.length > 0) {
                                for (String included : preExecuteIncludes) {
                                    if (method.getName().equals(included)) {
                                        isExecutable = true;
                                        break;
                                    }
                                }
                            }
                            // excluded method or not
                            if (preExecuteExcludes.length > 0) {
                                for (String excluded : preExecuteExcludes) {
                                    if (method.getName().equals(excluded)) {
                                        isExecutable = false;
                                        break;
                                    }
                                }
                            }
                            if (isExecutable) {
                                stackedPreExecuteInvocations.push(method);
                            }
                        } else {
                            log.warn("@PreExecuteMethod method should be public. (" + method.toGenericString() + ")");
                        }
                    }
                }
                actionClass = actionClass.getSuperclass();
            }
            Method stackedPreExecuteMethod = null;
            while (!stackedPreExecuteInvocations.empty() &&
                    (stackedPreExecuteMethod = stackedPreExecuteInvocations.pop()) != null) {
                stackedPreExecuteMethod.invoke(action, (Object[]) null);
            }

            // invoke @Execute method
            Object result = invocation.proceed();

            // post execute
            PostExecute postExecuteConfig = invokeMethod.getAnnotation(PostExecute.class);
            boolean postExecuteDefaultExecute = (postExecuteConfig != null) ? postExecuteConfig.autoExecute() : true;
            String[] postExecuteIncludes = (postExecuteConfig != null) ? postExecuteConfig.includeMethods() : new String[0];
            String[] postExecuteExcludes = (postExecuteConfig != null) ? postExecuteConfig.excludeMethods() : new String[0];
            actionClass = action.getClass();
            Stack<Method> stackedPostExecuteInvocations = new Stack<Method>();
            while (actionClass != Object.class) {
                Method[] methods = actionClass.getDeclaredMethods();
                for (Method method : methods) {
                    PostExecuteMethod methodConfig = method.getAnnotation(PostExecuteMethod.class);
                    if (methodConfig != null) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            boolean isExecutable = (postExecuteDefaultExecute) ? methodConfig.autoExecute() : false;
                            // included method or not
                            if (postExecuteIncludes.length > 0) {
                                for (String included : postExecuteIncludes) {
                                    if (method.getName().equals(included)) {
                                        isExecutable = true;
                                        break;
                                    }
                                }
                            }
                            // excluded method or not
                            if (postExecuteExcludes.length > 0) {
                                for (String excluded : postExecuteExcludes) {
                                    if (method.getName().equals(excluded)) {
                                        isExecutable = false;
                                        break;
                                    }
                                }
                            }
                            if (isExecutable) {
                                stackedPostExecuteInvocations.push(method);
                            }
                        } else {
                            log.warn("@PostExecuteMethod method should be public. (" + method.toGenericString() + ")");
                        }
                    }
                }
                actionClass = actionClass.getSuperclass();
            }
            Method stackedPostExecuteMethod = null;
            while (!stackedPostExecuteInvocations.empty()
                    && (stackedPostExecuteMethod = stackedPostExecuteInvocations.pop()) != null) {
                stackedPostExecuteMethod.invoke(action, (Object[]) null);
            }
            return result;
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}
