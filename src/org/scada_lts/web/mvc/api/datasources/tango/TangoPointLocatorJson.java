package org.scada_lts.web.mvc.api.datasources.tango;

import com.serotonin.mango.vo.dataSource.tango.TangoPointLocatorVO;
import org.scada_lts.web.mvc.api.datasources.DataPointLocatorJson;

/**
 * @author GP Orcullo
 */

public class TangoPointLocatorJson extends DataPointLocatorJson {

    private String attribute;
    private boolean settable;

    public TangoPointLocatorJson() {}

    public TangoPointLocatorJson(TangoPointLocatorVO pointLocatorVO) {
        super(pointLocatorVO);
        this.attribute = pointLocatorVO.getAttribute();
        this.settable = pointLocatorVO.isSettable();
    }

    @Override
    public TangoPointLocatorVO parsePointLocatorData() {
        TangoPointLocatorVO  plVO = new TangoPointLocatorVO();
        plVO.setSettable(this.isSettable());
        plVO.setDataTypeId(this.getDataTypeId());
        plVO.setAttribute(this.getAttribute());
        return plVO;
    }

    public boolean isSettable() {
        return settable;
    }

    public void setSettable(boolean settable) {
        this.settable = settable;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


}
