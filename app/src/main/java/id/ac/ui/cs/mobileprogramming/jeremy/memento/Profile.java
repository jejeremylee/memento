package id.ac.ui.cs.mobileprogramming.jeremy.memento;

public class Profile {
        private String name;
        private String nickName;
        private String phone;
        private String address;
        private String birthday;
        private int image;

        public Profile(String name, String nickName, String phone, String birthday, String address, int image) {
            this.name = name;
            this.nickName = nickName;
            this.address = address;
            this.birthday = birthday;
            this.phone = phone;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() { return nickName; }

        public void setNickName(String nickName) {
        this.nickName = nickName;
    }

        public String getAddress() {
        return address;
    }

        public void setAddress(String address) {
        this.address = address;
    }

        public String getBirthday() {
        return birthday;
    }

        public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getImg() {
            return image;
        }

        public void setImg(int image) {
            this.image = image;
        }
}
