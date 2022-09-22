package org.springframework.samples.petclinic.patterns.builder;

import static org.junit.Assert.assertThat;

import org.jpatterns.gof.BuilderPattern;
import org.jpatterns.gof.BuilderPattern.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@BuilderPattern()
public class BuilderPatternWithLombokApplication {
    @Getter
    @Builder(toBuilder = true)
    @ToString
    @Product()
    static class Widget {
        private final String name;
        private final int id;
    }

    public static void main(String[] args){
        Widget testWidget = Widget.builder()
                                    .name("foo")
                                    .id(1)
                                    .build();

        System.out.println(testWidget);

        Widget.WidgetBuilder widgetBuilder = testWidget.toBuilder();

        Widget newWidget = widgetBuilder.id(2).build();

        System.out.println(newWidget);
    }

}
