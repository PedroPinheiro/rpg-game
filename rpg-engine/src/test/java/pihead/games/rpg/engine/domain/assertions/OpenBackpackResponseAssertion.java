package pihead.games.rpg.engine.domain.assertions;

import pihead.games.rpg.engine.domain.OpenBackpack.ResponseModel;
import pihead.games.rpg.engine.domain.OpenBackpack.ResponseModel.*;
import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.domain.entities.items.Weapon;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenBackpackResponseAssertion {

    public static EnemiesAssert assertThatOpenBackpackResponse(ResponseModel responseModel) {
        return new EnemiesAssert(responseModel);
    }

    public static class EnemiesAssert {

        ResponseModel responseModel;

        private EnemiesAssert(ResponseModel responseModel) {
            this.responseModel = responseModel;
        }

        public void hasItemsOf(Backpack backpack) {

            for (Item item : backpack.getItems()) {

                Optional<ItemModel> optionalItemModel =
                        responseModel.getItems().stream().filter(i -> i.getId() == item.getId()).findFirst();

                assertThat(optionalItemModel.isPresent()).isNotNull();

                ItemModel itemModel = optionalItemModel.get();
                assertThat(itemModel.getName()).isEqualTo(item.getType().getName());

                if (item instanceof HealthItem) {

                    HealthItem healthItem = (HealthItem) item;
                    assertThat(itemModel.getType()).isEqualTo(ItemTypeModel.HEALTH);
                    assertThat(itemModel.getHealthPower()).isEqualTo(healthItem.getType().getHealthPower());
                    assertThat(itemModel.getWeaponDamage()).isEqualTo(0);

                } else if (item instanceof Weapon) {

                    Weapon weapon = (Weapon) item;
                    assertThat(itemModel.getType()).isEqualTo(ItemTypeModel.WEAPON);
                    assertThat(itemModel.getHealthPower()).isEqualTo(0);
                    assertThat(itemModel.getWeaponDamage()).isEqualTo(weapon.getType().getDamage());
                }

            }


        }

    }

}