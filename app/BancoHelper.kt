import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) : SQLiteOpenHelper(context, "campus.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
        CREATE TABLE eventos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            data TEXT,
            local TEXT,
            descricao TEXT,
            favorito INTEGER DEFAULT 0
        )
        """.trimIndent())
            }
        override fun onUpgrade(
            db: SQLiteDatabase,
            oldVersion: Int,
            newVersion: Int
        ){ }

    fun inserir(evento: Evento): Long {
        val valores = ContentValues().apply {
            put("nome", evento.nome)
            put("data", evento.data)
            put("local", evento.local)
            put("descricao", evento.descricao)
            put("favorito", if (evento.favorito) 1 else 0)
        }
        return writableDatabase.insert(
            "eventos",
            null,
            valores
        )
    }
}