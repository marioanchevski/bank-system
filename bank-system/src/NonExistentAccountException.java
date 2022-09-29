public class NonExistentAccountException extends Exception {
    public NonExistentAccountException(long id) {
        super(String.format("The account with ID:%d does not exist.", id));
    }
}
