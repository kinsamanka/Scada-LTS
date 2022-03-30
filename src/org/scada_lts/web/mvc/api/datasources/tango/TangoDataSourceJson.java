package org.scada_lts.web.mvc.api.datasources.tango;

import com.serotonin.mango.vo.dataSource.tango.TangoDataSourceVO;
import org.scada_lts.web.mvc.api.datasources.DataSourceJson;

/**
 * @author GP Orcullo
 */

public class TangoDataSourceJson extends DataSourceJson {

    private String deviceID;
    private String hostName;
    private int port = 10000;

    public TangoDataSourceJson() {}

    public TangoDataSourceJson(TangoDataSourceVO dataSourceVO) {
        super(dataSourceVO);
        this.deviceID = dataSourceVO.getDeviceID();
        this.hostName = dataSourceVO.getHostName();
        this.port = dataSourceVO.getPort();
    }

    @Override
    public TangoDataSourceVO createDataSourceVO() {
        TangoDataSourceVO vo = new TangoDataSourceVO();
        vo.setId(this.getId());
        vo.setName(this.getName());
        vo.setXid(this.getXid());
        vo.setEnabled(this.isEnabled());
        vo.setDeviceID(this.getDeviceID());
        vo.setHostName(this.getHostName());
        vo.setPort(this.getPort());
        return vo;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
