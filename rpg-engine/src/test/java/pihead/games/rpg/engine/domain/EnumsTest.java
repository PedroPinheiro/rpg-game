package pihead.games.rpg.engine.domain;


import org.junit.Test;

public class EnumsTest {

    @Test
    public void superficialEnumCodeCoverageTest() {

        superficialEnumCodeCoverage(EnterRoom.ResponseModel.DirectionModel.class);
        superficialEnumCodeCoverage(EnterRoom.ResponseModel.ItemTypeModel.class);
        superficialEnumCodeCoverage(OpenBackpack.ResponseModel.ItemTypeModel.class);
    }

    public static void superficialEnumCodeCoverage(Class<? extends Enum<?>> enumClass) {
        try {
            for (Object o : (Object[])enumClass.getMethod("values").invoke(null)) {
                enumClass.getMethod("valueOf", String.class).invoke(null, o.toString());
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
