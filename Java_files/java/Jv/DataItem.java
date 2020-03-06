package ltd.nickolay.listclick.Jv;

import android.os.Parcel;
import android.os.Parcelable;


public class DataItem implements Parcelable {
    private int di_Image;
    private String di_Title;
    private String di_SubText;

    public DataItem(int image, String title, String subtext) {
        di_Image = image;
        di_Title = title;
        di_SubText = subtext;
    }

    public int getImage() {
        return di_Image;
    }

    public String getTitle() {
        return di_Title;
    }

    public String getSubText() {
        return di_SubText;
    }

    protected DataItem(Parcel in) {
        di_Image = in.readInt();
        di_Title = in.readString();
        di_SubText = in.readString();
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(di_Image);
        dest.writeString(di_Title);
        dest.writeString(di_SubText);
    }
}
