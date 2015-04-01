package com.miholap.dependencyinjection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by user on 4/1/2015.
 */
public class Util {
    public static Method getSetter(String fieldName, Class objectClass){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(objectClass);
            PropertyDescriptor propertyDescriptor =
                    Arrays.asList(beanInfo.getPropertyDescriptors()).stream()
                    .filter(pd -> pd.getName().equals(fieldName))
                            .findFirst().get();
            return propertyDescriptor != null ? propertyDescriptor.getWriteMethod() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
