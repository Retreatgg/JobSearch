package com.example.demo.specifications;

import com.example.demo.model.Vacancy;
import org.springframework.data.jpa.domain.Specification;

public class VacancySpecification {

    public static Specification<Vacancy> isActive(Boolean bool) {
        return (r, q, cb) -> {
            if (bool == null) {
                return cb.conjunction();
            }
            return cb.equal(r.get("isActive"), bool);
        };
    }

    public static Specification<Vacancy> betweenExp(int expFrom, int expTo) {
        return (r, q, cb) -> {
            if(expFrom == 0 && expTo == 0) {
                return cb.conjunction();
            }
            return cb.and(
                    cb.greaterThanOrEqualTo(r.get("expFrom"), expFrom),
                    cb.lessThanOrEqualTo(r.get("expTo"), expTo)
            );
        };
    }

}
