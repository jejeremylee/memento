package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profiles {
    @PrimaryKey(autoGenerate = true)
    public int profileId;

    @ColumnInfo(name = "nameProfile")
    public String nameProfile;

    @ColumnInfo(name = "nickNameProfile")
    public String nickNameProfile;

    @ColumnInfo(name = "phoneProfile")
    public String phoneProfile;

    @ColumnInfo(name = "birthdayProfile")
    public String birthdayProfile;

    @ColumnInfo(name = "addressProfile")
    public String addressProfile;

    @ColumnInfo(name = "imageProfile")
    public int imageProfile;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public String getPhoneProfile() {
        return phoneProfile;
    }

    public void setPhoneProfile(String phoneProfile) {
        this.phoneProfile = phoneProfile;
    }

    public String getNickNameProfile() { return nickNameProfile; }

    public void setNickNameProfile(String nickName) {
        this.nickNameProfile = nickName;
    }

    public String getAddressProfile() {
        return addressProfile;
    }

    public void setAddressProfile(String addressProfile) {
        this.addressProfile = addressProfile;
    }

    public String getBirthdayProfile() {
        return birthdayProfile;
    }

    public void setBirthdayProfile(String birthdayProfile) {
        this.birthdayProfile = birthdayProfile;
    }

    public int getImg() {
        return imageProfile;
    }

    public void setImg(int image) {
        this.imageProfile = image;
    }

}
