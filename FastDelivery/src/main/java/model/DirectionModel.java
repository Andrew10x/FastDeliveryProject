package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionModel {
    private int DirectionId;
    private int CityFromId;
    private int CityToId;
    private int Distance;
}
