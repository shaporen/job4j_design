package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisUnknownAgain() {
        Box box = new Box(4, -3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isExist() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isEdgeLessThanZeroThenVertexIsMinusOne() {
        Box box = new Box(3, -3);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void isUnknownThenVertexIsMinusOne() {
        Box box = new Box(3, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void isNotExist() {
        Box box = new Box(3, 3);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenEdgeFourThenAreaOverTwoHundred() {
        Box box = new Box(0, 4);
        double area = box.getArea();
        assertThat(area).isCloseTo(201.06, offset(0.01D));
    }

    @Test
    void whenEdgeFourAndVertexEigthThenAreaNinetySix() {
        Box box = new Box(8, 4);
        double area = box.getArea();
        assertThat(area).isCloseTo(96, offset(0.01D));
    }

    @Test
    void whenEdgeTwoAndVertexTwoThenZero() {
        Box box = new Box(2, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(0D);
    }
}