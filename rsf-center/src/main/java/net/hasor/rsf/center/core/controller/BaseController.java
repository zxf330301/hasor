/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.rsf.center.core.controller;
import net.hasor.core.AppContext;
import net.hasor.core.InjectMembers;
import net.hasor.mvc.support.AbstractWebController;
import net.hasor.plugins.valid.ValidData;
import net.hasor.plugins.valid.ValidUtils;
import net.hasor.rsf.center.core.ioc.InjectUtils;
import net.hasor.rsf.center.core.login.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @version : 2015年7月28日
 * @author 赵永春(zyc@hasor.net)
 */
public class BaseController extends AbstractWebController implements InjectMembers {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    //
    public void doInject(AppContext appContext) {
        try {
            InjectUtils.inject(this, appContext);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    protected String getRequestURI() {
        return this.getRequest().getRequestURI();
    }
    protected UserInfo getLoginUser() {
        return new UserInfo();
    }
    protected ValidData validForm(String validName, Object paramObj) {
        return ValidUtils.doValid(validName, paramObj);
    }
}