/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.solder.test.core.requires.beans;

import javax.enterprise.inject.Typed;
import javax.inject.Named;

import org.jboss.seam.solder.core.Requires;
import org.jboss.seam.solder.test.core.requires.CommonInterface;
import org.jboss.seam.solder.test.core.requires.Jaguar;

@Requires({"org.jboss.seam.solder.test.core.requires.Tiger", "org.jboss.seam.solder.test.core.requires.Jaguar",
        "java.lang.Integer"})
@Typed({CommonInterface.class, DisabledOptionalBeanWithSupertypeDependency.class})
@Named
public class DisabledOptionalBeanWithSupertypeDependency extends Jaguar implements CommonInterface {

}
