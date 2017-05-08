package lt.welovedotnot.ktu_ais_app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.RealmClass

/**
 * Created by simonas on 5/2/17.
 */

@RealmClass
open class GetGradesResponse: RealmObject() {

    @SerializedName("name")
    @Expose
    open var name: String? = null

    @SerializedName("id")
    @Expose
    open var id: String? = null

    @SerializedName("semester")
    @Expose
    open var semester: String? = null

    @SerializedName("module_code")
    @Expose
    open var moduleCode: String? = null

    @SerializedName("module_name")
    @Expose
    open var moduleName: String? = null

    @SerializedName("semester_number")
    @Expose
    open var semesterNumber: String? = null

    @SerializedName("language")
    @Expose
    open var language: String? = null

    @SerializedName("profestor")
    @Expose
    open var profestor: String? = null

    @SerializedName("typeId")
    @Expose
    open var typeId: String? = null

    @SerializedName("type")
    @Expose
    open var type: String? = null

    @SerializedName("week")
    @Expose
    open var week: String? = null

    @Ignore
    @SerializedName("mark")
    @Expose
    open var mark: List<String> = listOf()

    open var rlMark: String? = null

    override fun equals(other: Any?): Boolean {
        if (other is GetGradesResponse) {
            return id == other.id && semesterNumber == other.semesterNumber && week == other.week
        } else {
            return super.equals(other)
        }
    }

    fun diff(model: GetGradesResponse, callback: (GradeUpdateModel)->(Unit)) {
        mark.forEachIndexed { index, item ->
            if (model.mark[index] != item) {
                val updateModel = GradeUpdateModel(
                        name = name!!,
                        type = type!!,
                        mark = item
                )
                callback.invoke(updateModel)
            }
        }
    }
}