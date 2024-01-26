package ru.nikidzawa.geo.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private String coordinates;
}
