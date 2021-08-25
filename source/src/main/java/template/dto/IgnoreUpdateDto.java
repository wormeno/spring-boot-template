package template.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IgnoreUpdateDto {
    public abstract class IgnoreLabelMixin {
        @JsonIgnore
        public abstract String getLabel();

    }
}
