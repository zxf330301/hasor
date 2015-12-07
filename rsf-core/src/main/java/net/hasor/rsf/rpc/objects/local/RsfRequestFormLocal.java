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
package net.hasor.rsf.rpc.objects.local;
import java.lang.reflect.Method;
import net.hasor.rsf.RsfBindInfo;
import net.hasor.rsf.RsfContext;
import net.hasor.rsf.RsfRequest;
import net.hasor.rsf.domain.RSFConstants;
import net.hasor.rsf.domain.RsfException;
import net.hasor.rsf.rpc.context.AbstractRsfContext;
import net.hasor.rsf.transform.protocol.OptionInfo;
import net.hasor.rsf.transform.protocol.RequestInfo;
import net.hasor.rsf.utils.RsfRuntimeUtils;
/**
 * RSF请求
 * @version : 2014年10月25日
 * @author 赵永春(zyc@hasor.net)
 */
public class RsfRequestFormLocal extends OptionInfo implements RsfRequest {
    private final AbstractRsfContext rsfContext;
    private final RequestInfo        requestInfo;
    private final RsfBindInfo<?>     bindInfo;
    private final Method             targetMethod;
    private final Class<?>[]         parameterTypes;
    private final Object[]           parameterObjects;
    private final long               receiveTime;
    //
    public RsfRequestFormLocal(RequestInfo requestInfo, Method targetMethod, Object[] parameterObjects, AbstractRsfContext rsfContext) throws RsfException {
        this.requestID = RsfRuntimeUtils.genRequestID();
        this.bindInfo = bindInfo;
        this.targetMethod = targetMethod;
        this.parameterTypes = targetMethod.getParameterTypes();
        this.parameterObjects = parameterObjects;
        this.rsfContext = rsfContext;
        this.receiveTime = System.currentTimeMillis();
    }
    @Override
    public String toString() {
        return "requestID:" + this.getRequestID() + " from Local," + this.bindInfo.toString();
    }
    //
    @Override
    public RsfBindInfo<?> getBindInfo() {
        return this.bindInfo;
    }
    @Override
    public byte getVersion() {
        return RSFConstants.Version_1;
    }
    @Override
    public long getRequestID() {
        return this.requestID;
    }
    @Override
    public String getSerializeType() {
        return this.bindInfo.getSerializeType();
    }
    @Override
    public boolean isLocal() {
        return true;
    }
    @Override
    public Method getMethod() {
        return this.targetMethod;
    }
    @Override
    public RsfContext getContext() {
        return this.rsfContext;
    }
    @Override
    public long getReceiveTime() {
        return this.receiveTime;
    }
    @Override
    public int getTimeout() {
        return this.bindInfo.getClientTimeout();
    }
    @Override
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }
    @Override
    public Object[] getParameterObject() {
        return this.parameterObjects;
    }
}