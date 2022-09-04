package ru.api.services;

import org.springframework.stereotype.Service;
import ru.api.modles.Pagination;

@Service
public class PaginationService {

    private final Integer MAX_LIMIT = 50;
    public static final Integer DEFAULT_LIMIT = 10;
    public static final Integer DEFAULT_OFFSET = 0;

    public Integer getLimit(String strLimit) {
        int limit = Integer.parseInt(strLimit);
        if (limit < 0) return DEFAULT_LIMIT;
        else if (limit > MAX_LIMIT) return MAX_LIMIT;
        else return limit;
    }

    public Integer getOffset(String strOffset) {
        int offset = Integer.parseInt(strOffset);
        if (offset < 0) return DEFAULT_OFFSET;
        else return offset;
    }

    public Pagination getPagination(String limit, String offset) {
        return Pagination.builder().limit(getLimit(limit)).offset(getOffset(offset)).build();
    }

    public Pagination getDefault() {
        return Pagination.builder().limit(DEFAULT_LIMIT).offset(DEFAULT_OFFSET).build();
    }
}
