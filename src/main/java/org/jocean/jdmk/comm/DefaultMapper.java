/*
 * @(#)file      DefaultMapper.java
 * @(#)author    Sun Microsystems, Inc.
 * @(#)version   1.13
 * @(#)date      07/10/01
 *
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 2007 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU General
 * Public License Version 2 only ("GPL") or the Common Development and
 * Distribution License("CDDL")(collectively, the "License"). You may not use
 * this file except in compliance with the License. You can obtain a copy of the
 * License at http://opendmk.dev.java.net/legal_notices/licenses.txt or in the 
 * LEGAL_NOTICES folder that accompanied this code. See the License for the 
 * specific language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file found at
 *     http://opendmk.dev.java.net/legal_notices/licenses.txt
 * or in the LEGAL_NOTICES folder that accompanied this code.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.
 * 
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * 
 *       "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding
 * 
 *       "[Contributor] elects to include this software in this distribution
 *        under the [CDDL or GPL Version 2] license."
 * 
 * If you don't indicate a single choice of license, a recipient has the option
 * to distribute your version of this file under either the CDDL or the GPL
 * Version 2, or to extend the choice of license to its licensees as provided
 * above. However, if you add GPL Version 2 code and therefore, elected the
 * GPL Version 2 license, then the option applies only if the new code is made
 * subject to such option by the copyright holder.
 * 
 *
 */


package org.jocean.jdmk.comm;



import javax.management.ObjectInstance;

import org.jocean.jdmk.ProxyMBeanInstantiationException;


/**
 * This class is used for mapping object instance into proxy class name
 * name.
 * Given an  ObjectInstance the mapper service should be able to give
 * the name of the Java class to use for representing the object as
 * a ProxyMBean.    To be coherent with a java extension, no class could be added in javax.management.
 * More generally, no class could be added in java.xxx or in javax.xxx.
 * The DefaultMapper behavior will follow these rules:
 * 1) The generated class name is obtained by appending "Proxy" at the end of the class name 
 * 2) If the generated class name obtained after rule 1, begins with "java." or "javax." (javax.management.monitor.GaugeMonitorProxy for example),
 *    generated class name will not belong to any package. In other term, we remove  package
 *    clause if package is javax.xxx or java.xxx
 *
 * @deprecated MBean proxies should be generated using {@link
 * javax.management.MBeanServerInvocationHandler#newProxyInstance
 * MBeanServerInvocationHandler.newProxyInstance}.  The class
 * <code>DefaultMapper</code> may be removed in a future version
 * of Java DMK.
 *
 */

public class DefaultMapper implements Mapper {

  /**
   * Gets name of a Java class to use for representing a proxyMBean.
   * 
   * @param instance The object instance of the object for which the
   * implementing name is requested.
   * @return The name of the Java class to use for representing the
   * proxyMBean.
   * @exception ProxyMBeanInstantiationException An error occurs.
   */
  public String getClassForProxyMBean(ObjectInstance instance) 
      throws ProxyMBeanInstantiationException {
        String name = instance.getClassName() ; 
        if ((name.startsWith("java."))
               || 
                (name.startsWith("javax."))
        ) 
        { 
                name = name.substring(name.lastIndexOf(".") + 1) ;
        } 
    return name + "Proxy" ;
  }

}

