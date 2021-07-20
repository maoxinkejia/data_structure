package com.zmx.study.datastructure;


import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.BiFunction;

import static java.util.Collections.emptyList;

@SpringBootApplication
@SpringBootConfiguration
public class DataStructureApplication {

    public static void main(String[] args) {
        List<A> l1 = new ArrayList<>();
        l1.add(new A(1, "11"));
        l1.add(new A(2, "22"));
        l1.add(new A(3, "33"));
        l1.add(new A(3, "44"));

        Map<Integer, A> map = new HashMap<>();
        l1.forEach(x -> map.putIfAbsent(x.getId(), x));

        System.out.println(map  );
    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    private static class A {
        private Integer id;
        private String name;
    }
}
