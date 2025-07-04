package com.github.son_daehyeon.global.infra.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
public abstract class BaseSchema {

    @Id
    String id;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (Objects.isNull(obj) || !getClass().equals(obj.getClass())) {
            return false;
        }

        BaseSchema that = (BaseSchema) obj;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {

        return id.hashCode();
    }
}
