package hulkstore_.model.dto.users;

import java.io.Serializable;

/**
 * This class represents the user model.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UsersDto implements Serializable
{
    /** 
     * This attribute maps to the column userId in the users table.
     */
    protected int userId;

    /** 
     * This attribute maps to the column user_Name in the users table.
     */
    protected String user_Name;

    /** 
     * This attribute maps to the column userPass in the users table.
     */
    protected String userPass;

    /** 
     * This attribute maps to the column identification in the users table.
     */
    protected String identification;

    /** 
     * This attribute maps to the column realName in the users table.
     */
    protected String realName;

    /** 
     * This attribute maps to the column surname in the users table.
     */
    protected String surname;

    /** 
     * This attribute maps to the column userProfile in the users table.
     */
    protected short userProfile;

    /** 
     * This attribute maps to the column state in the users table.
     */
    protected short state;

    /**
     * Empty Constructor.     * 
     */
    public UsersDto() {}
    
    /**
     * Constructor.
     * 
     * @param userId
     * @param user_Name
     * @param identification
     * @param realName
     * @param surname
     * @param userProfile
     * @param state 
     */    
    public UsersDto(int userId, String user_Name, String identification, String realName, String surname, short userProfile, short state)
    {
        this.userId = userId;
        this.user_Name = user_Name;
        this.identification = identification;
        this.realName = realName;
        this.surname = surname;
        this.userProfile = userProfile;
        this.state = state;
    }

    /**
     * Constructor.
     * 
     * @param userId
     * @param user_Name
     * @param userPass
     * @param identification
     * @param realName
     * @param surname
     * @param userProfile
     * @param state 
     */
    public UsersDto(int userId, String user_Name, String userPass, String identification, String realName, String surname, short userProfile, short state) {
        this.userId = userId;
        this.user_Name = user_Name;
        this.userPass = userPass;
        this.identification = identification;
        this.realName = realName;
        this.surname = surname;
        this.userProfile = userProfile;
        this.state = state;
    }

    /**
     * Gets the value of userId.
     * 
     * @return int
     */
    public int getUserId() { return userId; }

    /**
     * Sets the value of userId.
     * 
     * @param userId
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the value of user_Name.
     * 
     * @return String
     */
    public String getUserName() { return user_Name; }

    /**
     * Sets the value of user_Name.
     * 
     * @param user_Name
     */
    public void setUserName(String user_Name) { this.user_Name = user_Name; }

    /**
     * Gets the value of userPass.
     * 
     * @return String
     */
    public String getUserPass() { return userPass; }

    /**
     * Sets the value of userPass.
     * 
     * @param userPass
     */
    public void setUserPass(String userPass) { this.userPass = userPass; }

    /**
     * Gets the value of identification.
     * 
     * @return String
     */
    public String getIdentification() { return identification; }

    /**
     * Sets the value of identification.
     * 
     * @param identification
     */
    public void setIdentification(String identification) { this.identification = identification; }

    /**
     * Gets the value of realName.
     * 
     * @return String
     */
    public String getRealName() { return realName; }

    /**
     * Sets the value of realName.
     * 
     * @param realName
     */
    public void setRealName(String realName) { this.realName = realName; }

    /**
     * Gets the value of surname.
     * 
     * @return String
     */
    public String getSurname() { return surname; }

    /**
     * Sets the value of surname.
     * 
     * @param surname
     */
    public void setSurname(String surname) { this.surname = surname; }

    /**
     * Gets the value of userProfile.
     * 
     * @return short
     */
    public short getUserProfile() { return userProfile; }

    /**
     * Sets the value of userProfile.
     * 
     * @param userProfile
     */
    public void setUserProfile(short userProfile) { this.userProfile = userProfile; }

    /**
     * Gets the value of state.
     * 
     * @return short
     */
    public short getState() { return state; }

    /**
     * Sets the value of state.
     * 
     * @param state
     */
    public void setState(short state) { this.state = state; }

    /**
     * Method 'createPk'
     * 
     * @return UsersPk
     */
    public UsersPk createPk() { return new UsersPk(userId); }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.Users: ");
        response.append("userId=").append(userId);
        response.append(", user_Name=").append(user_Name);
        response.append(", userPass=").append(userPass);
        response.append(", identification=").append(identification);
        response.append(", realName=").append(realName);
        response.append(", surname=").append(surname);
        response.append(", userProfile=").append(userProfile);
        response.append(", state=").append(state);
        return response.toString();
    }
}