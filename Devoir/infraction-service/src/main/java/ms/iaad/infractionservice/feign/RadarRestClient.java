package ms.iaad.infractionservice.feign;

import ms.iaad.infractionservice.models.Radar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "radar-service")
public interface RadarRestClient {
    @GetMapping("radars/{id}")
    Radar getByRadarById(@PathVariable Long id);
}
