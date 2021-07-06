/*
 * Copyright 2014 Jeanfrancois Arcand
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
 package com.lightsnail.schoolmanager.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements BeanFactoryAware {

    private static BeanFactory springBeanFactory;

    @Override
    public synchronized void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        springBeanFactory = beanFactory;
    }

    public synchronized static <T> T getBean(Class<? extends T> klass) {
        return springBeanFactory.getBean(klass);
    }
    
    public synchronized static Object getBean(String beanName) {
        return springBeanFactory.getBean(beanName);
    }
}