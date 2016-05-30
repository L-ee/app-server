package test;

public class DemoTest {
	private static class Person {
		String name;
		String age;

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}

	}

	public static Object getObjectFromString(String str) {
		String[] arr = str.split(",");
		Person person = new Person();
		person.name = arr[0];
		person.age = arr[1];
		return person;
	}

	public static void main(String[] args) {
		String test = "wanggang,20";
		Object obj = getObjectFromString(test);
		System.out.println(((Person)obj).toString());
	}
}
