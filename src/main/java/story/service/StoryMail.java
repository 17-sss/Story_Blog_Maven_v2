package story.service;

public class StoryMail {
	// �ӽú�й�ȣ ������
	public String temp_pwd (String tempPassword) {
		for (int i = 0; i < 8; i++) {
			int random_Val = (int) (Math.random() * 62);
			
			if (random_Val < 10) {
				tempPassword += random_Val;
			} else if (random_Val > 35) {
				tempPassword += (char) (random_Val + 61);
			} else {
				tempPassword += (char) (random_Val + 55);
			}
		}
		return tempPassword;
	}
}
