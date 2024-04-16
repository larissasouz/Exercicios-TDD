package exercicio02;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class PersonDAOTest {

	@Test
    public void testIsValidToInclude_ValidPerson() {
        Person person = new Person("John Doe", 30, 1);
        person.addEmail(new Email(1, "john@example.com"));
        PersonDAO personDAO = new PersonDAO();
        List<String> errors = personDAO.isValidToInclude(person);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testIsValidToInclude_InvalidName() {
        Person person = new Person("John", 30, 1);
        person.addEmail(new Email(1, "john@example.com"));
        
        PersonDAO personDAO = new PersonDAO();
        
        List<String> errors = personDAO.isValidToInclude(person);
        assertFalse(errors.isEmpty());
        assertEquals("O nome deve ser composto por ao menos 2 partes e conter apenas letras", errors.get(0));
    }

    @Test
    public void testIsValidToInclude_InvalidAge() {
        Person person = new Person("John Doe", 0, 1);
        person.addEmail(new Email(1, "john@example.com"));
        PersonDAO personDAO = new PersonDAO();

        List<String> errors = personDAO.isValidToInclude(person);

        assertFalse(errors.isEmpty());
        assertEquals("A idade deve estar no intervalo [1, 200]", errors.get(0));
    }

    @Test
    public void testIsValidToInclude_NoEmail() {
        Person person = new Person("John Doe", 30, 1);

        PersonDAO personDAO = new PersonDAO();

        List<String> errors = personDAO.isValidToInclude(person);

        assertFalse(errors.isEmpty());
        assertEquals("O objeto Person deve ter pelo menos um objeto da classe Email associado", errors.get(0));
    }

    @Test
    public void testIsValidToInclude_InvalidEmailFormat() {
        Person person = new Person("John Doe", 30, 1);
        person.addEmail(new Email(1, "invalid-email-format"));

        PersonDAO personDAO = new PersonDAO();

        List<String> errors = personDAO.isValidToInclude(person);

        assertFalse(errors.isEmpty());
        assertEquals("O formato do email é inválido", errors.get(0));
    }
}
