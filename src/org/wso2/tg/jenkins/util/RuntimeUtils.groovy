package org.wso2.tg.jenkins.util

import org.wso2.tg.jenkins.Properties

/**
 * Util methods required by the TG runtime.
 */

def increaseTestGridRuntimeMemory(min, max) {
    def props = Properties.instance
    sh """
          echo ${props.TESTGRID_NAME}
          cd ${props.TESTGRID_DIST_LOCATION}
          cd ${props.TESTGRID_NAME}
          sed -i 's/-Xms256m -Xmx1024m/-Xmx${min} -Xms${max}/g' testgrid
        """
}