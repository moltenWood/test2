import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable

object ClipBoard {
    /**
     * 将字符串复制到剪切板。
     */
    fun setSysClipboardText(writeMe: String?) {
        val clip: Clipboard = Toolkit.getDefaultToolkit().getSystemClipboard()
        val tText: Transferable = StringSelection(writeMe)
        clip.setContents(tText, null)
    }
}
