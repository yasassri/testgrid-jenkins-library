package org.wso2.tg.jenkins.util

/**
 * Util methods required by the TG runtime.
 */
class RuntimeUtil {

    def increaseTestGridRuntimeMemory(min, max) {
        sh """
          echo ${TESTGRID_NAME}
          cd ${TESTGRID_DIST_LOCATION}
          cd ${TESTGRID_NAME}
          sed -i 's/-Xms256m -Xmx1024m/-Xmx${min} -Xms${max}/g' testgrid
        """
    }
}
