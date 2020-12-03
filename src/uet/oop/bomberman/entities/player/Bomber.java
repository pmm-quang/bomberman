package uet.oop.bomberman.entities.player;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class Bomber extends MovingEntity {

    private boolean createBom;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.steps = 0;
        this.speed = 3;
        hp = 1;
        currentDirection = Direction.DOWN;
        spriteDead = new Sprite[] {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
        setRectBox(new RectBox(this.x + 2, this.y + 5, Sprite.SCALED_SIZE - 9, Sprite.SCALED_SIZE - 4));

    }

    @Override
    public void move(double time) {
        int xa = 0, ya = 0;
        if (steps > 0) {
            switch (currentDirection) {
                case UP: ya -= speed; break;
                case DOWN: ya += speed; break;
                case LEFT: xa -= speed; break;
                case RIGHT: xa += speed; break;
            }
            if (!canMove(this.x + xa, this.y + ya)) {
                this.x += xa;
                this.y += ya;
                switch (currentDirection) {
                    case UP:
                        this.img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2,2, time).getFxImage();
                        break;
                    case DOWN:
                        this.img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2,2, time).getFxImage();
                        break;
                    case RIGHT:
                        this.img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,2, time).getFxImage();
                        break;
                    case LEFT:
                        this.img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,2, time).getFxImage();
                        break;
                }
                steps--;
            } else {
                steps = 0;
            }
        }
    }


    @Override
    public boolean canMove(int x, int y) {
        this.rectBox.setPosition(x, y);
        for (Entity e : Board.getStillObjects()) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                return true;
            }
        }
        for (Entity e : Board.getEntities()) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }

    @Override
    public void dead() {
        if (!isLives()) {
            int index = timeDie / 10;
            setImg(spriteDead[index].getFxImage());
            timeDie++;
            if (timeDie > 28) {
                Sound.play("endgame3", 0);
                BombermanGame.timer.stop();
            }
        }
    }

    @Override
    public boolean isLives() {
        if (hp <= 0) {
            isLive = false;
        }
        return isLive;
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;

        if (this.getRectBox().checkCollision(other.getRectBox())) {
            if (other instanceof Item) {
                Board.buff((Item)other);
                Sound.play("Item", 0);
                 return false;
            }
            if (other instanceof Enemy) {
                isLive = false;
                return true;
            }

            if (other instanceof Portal) {
                Board.setNextLv(true);
                System.out.println(Board.nextLv);
                return false;
            }
        }
       return this.getRectBox().checkCollision(other.getRectBox());
    }



    public Bom createBom() {
        if (createBom) {
            if (Board.getBomList().size() >= Board.maxBom) {
                return null;
            }
            int realX = this.getX() / Sprite.SCALED_SIZE;
            int realY = this.getY() / Sprite.SCALED_SIZE;
            int countX = this.getX() % Sprite.SCALED_SIZE;
            int countY = this.getY() % Sprite.SCALED_SIZE;
            if (countX > 15) realX++;
            if (countY > 15) realY++;

            Bom bom = new Bom(realX, realY, Sprite.bomb.getFxImage(),BombermanGame.time);
            for (Entity bom1: Board.getBomList()) {
                if (bom.isColliding(bom1)) {
                    return null;
                }
            }
            Sound.play("BOM_SET", 0);
            return bom;
        }
        return null;
    }

    public boolean isCreateBom() {
        return createBom;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        currentDirection = direction;
    }

    public void setCreateBom(boolean createBom) {
        this.createBom = createBom;
    }

}
