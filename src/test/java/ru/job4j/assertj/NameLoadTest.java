package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkValidateStringHasNotSignEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"ivan=2", "ivan"};
        String name = "ivan";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void checkValidateStringStartSignEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"ivan=2", "=ivan"};
        String name = "=ivan";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkValidateStringNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"  ivan=2", "ivan="};
        String name = "ivan=";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain a value");
    }
}