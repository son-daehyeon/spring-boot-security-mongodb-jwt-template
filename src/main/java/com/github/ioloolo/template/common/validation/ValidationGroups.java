package com.github.ioloolo.template.common.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({
        ValidationGroups.NotEmptyGroup.class,
        ValidationGroups.LengthGroup.class,
        ValidationGroups.PatternGroup.class
})
public interface ValidationGroups {

    interface NotEmptyGroup {}

    interface LengthGroup {}

    interface PatternGroup {}
}
