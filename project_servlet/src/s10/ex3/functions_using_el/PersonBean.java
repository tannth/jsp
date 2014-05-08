package s10.ex3.functions_using_el;

public class PersonBean {
	private String name;
	private int age;
	private String address;

	public PersonBean(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public static String displayPersonDetails(String name, int age,
			String address) {
		return "Name" + name + "-Age:" + age + "-Address:" + address;
	}

}
