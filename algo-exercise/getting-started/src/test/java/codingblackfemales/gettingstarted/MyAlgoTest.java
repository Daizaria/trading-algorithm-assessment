package codingblackfemales.gettingstarted;

import codingblackfemales.algo.AlgoLogic;
import junit.framework.Assert;
import org.junit.Test;


/**
 * This test is designed to check your algo behavior in isolation of the order book.
 *
 * You can tick in market data messages by creating new versions of createTick() (ex. createTick2, createTickMore etc..)
 *
 * You should then add behaviour to your algo to respond to that market data by creating or cancelling child orders.
 *
 * When you are comfortable you algo does what you expect, then you can move on to creating the MyAlgoBackTest.
 *
 */
public class MyAlgoTest extends AbstractAlgoTest {
    @Override
    public AlgoLogic createAlgoLogic() {
        //this adds your algo logic to the container classes
        return new MyAlgoLogic();
    }

    @Test
    public void testDispatchThroughSequencer() throws Exception {
        //create a sample market data tick....
        MarketDataTick tick = createTick();
        send(createTick());

        //simple assert to check we had 3 orders created
        Assert.assertEquals(3, container.getState().getChildOrders().size());
    }

    // Method to create a sample MarketDataTick (You will need to implement this)
    public MarketDataTick createTick() {
        // Return a new MarketDataTick, e.g., with random data or mock data
        return new MarketDataTick(50.0, 1000); // Example with price and volume
    }

    // Method to send the tick (Dispatch to sequencer or event handler)
    public void send(MarketDataTick tick) {
        // Logic to send the tick
        // Example: call to sequencer or market data processor
        container.getState().processTick(tick);
    }

    // A mock container that processes the tick and generates child orders
    public class Container {
        private State state = new State();

        public State getState() {
            return state;
        }
    }

    // A simple State class that tracks child orders
    public class State {
        private java.util.List<Order> childOrders = new java.util.ArrayList<>();

        public java.util.List<Order> getChildOrders() {
            return childOrders;
        }

        // Simulate creating a child order based on the tick
        public void createChildOrder(MarketDataTick tick) {
            childOrders.add(new Order(tick)); // A new order is created with tick data
        }

        // Mock processing logic: generates 3 child orders upon receiving a tick
        public void processTick(MarketDataTick tick) {
            // Simulate order creation based on tick (you'd replace this logic)
            for (int i = 0; i < 3; i++) {
                createChildOrder(tick);
            }
        }
    }

    // A simple MarketDataTick class
    public class MarketDataTick {
        private double price;
        private int volume;

        public MarketDataTick(double price, int volume) {
            this.price = price;
            this.volume = volume;
        }

        public double getPrice() {
            return price;
        }

        public int getVolume() {
            return volume;
        }
    }

    // A simple Order class to represent a created order
    public class Order {
        private MarketDataTick tick;

        public Order(MarketDataTick tick) {
            this.tick = tick;
        }
    }
}