package com.mecalux.prueba.common.base;

import java.util.Arrays;
import java.util.List;

public enum Family {

    EST(Arrays.asList(Racks.A, Racks.B, Racks.C)),
    ROB(Arrays.asList(Racks.A, Racks.C, Racks.D));

    private final List<Racks> racks;

    Family(List<Racks> racks) {
        this.racks = racks;
    }

    public List<Racks> getRacks() {
        return racks;
    }

}


