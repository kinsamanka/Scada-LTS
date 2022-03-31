package org.scada_lts.web.mvc.api.datasources.tango;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.serotonin.mango.Common;
import com.serotonin.mango.vo.User;
import fr.esrf.TangoApi.DeviceProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GP Orcullo
 */

@Controller
@RequestMapping(value = "/api/datasources/tango")
public class TangoController {

    private static final Log LOG = LogFactory.getLog(TangoController.class);

    @PostMapping(value = "/deviceinfo")
    public ResponseEntity<Map<String, Object>> tangoTest(
            @RequestBody ObjectNode data, HttpServletRequest request) {

        try {
            User user = Common.getUser(request);
            if(user != null) {
                ObjectMapper mapper = new ObjectMapper();
                TangoDataSourceJson ds = mapper.convertValue(data.get("datasource"), TangoDataSourceJson.class);
                String devName = String.format("tango://%s:%d/%s", ds.getHostName(), ds.getPort(), ds.getDeviceID());
                Map<String, Object> response = new HashMap<>();
                DeviceProxy dp = null;

                try {
                    dp = new DeviceProxy(devName);
                    LOG.debug(String.format("Connected to device: %s", devName));
                } catch (Exception e) {
                    LOG.error(String.format("Failed to connect to device: %s", devName));
                    response.put("status", "failed");
                    response.put("description", "Failed to establish a connection");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                response.put("status", "success");
                response.put("value", dp.get_info().toString());
                response.put("response", "Hello World!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}