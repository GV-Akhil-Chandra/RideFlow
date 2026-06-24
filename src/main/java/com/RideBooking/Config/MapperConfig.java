package com.RideBooking.Config;

import com.RideBooking.DTO.PointDTO;
import com.RideBooking.Utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper getMapperConfig(){
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(PointDTO.class, Point.class).setConverter(context -> {
            PointDTO PointDTO = context.getSource();
            return GeometryUtil.createPoint(PointDTO);
        });

        mapper.typeMap(Point.class, PointDTO.class).setConverter(context -> {
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(),
                    point.getY()
            };
            return new PointDTO(coordinates);
        });

        return mapper;
    }
}
