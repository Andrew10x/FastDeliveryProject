package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersModel {
    java.util.List<OrderJoinedModel> data;
    List<StatusesModel> statuses;
    List<CityModel> cities;
}
