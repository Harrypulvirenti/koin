/*
 * Copyright 2017-2018 the original author or authors.
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
package org.koin.core.instance

import org.koin.core.bean.BeanDefinition
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.ScopeInstance

/**
 * Factory Instance Holder
 *
 * @author Arnaud Giuliani
 */
class FactoryInstance<T>(beanDefinition: BeanDefinition<T>) :
    Instance<T>(beanDefinition) {

    override fun release(scope: ScopeInstance?) {}

    override fun isCreated(scope: ScopeInstance?): Boolean = false

    override fun <T> get(scope: ScopeInstance?, parameters: ParametersDefinition?): T {
        return create(beanDefinition, parameters)
    }
}