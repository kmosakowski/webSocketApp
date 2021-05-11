package pl.test.websocketapp.common;

/**
 * Mógłbym zrezygnować z tego interfejsu, ale w przypadky gdy aplikacja by się rozrozła,
 * lub instrumenty byłyby rozdzielone per osobne obiekty, to ten interfejs jest jak znalazł
 */
public interface Instrument {

    Product getInstrument();
}
