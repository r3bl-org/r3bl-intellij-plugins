package com.r3bl.copyselection

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.awt.datatransfer.StringSelection
import java.nio.file.Path
import kotlin.io.path.relativeTo

class CopySelectionPathAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val relativePath = getRelativePath(project, virtualFile) ?: run {
            showError(project, "Could not determine relative path")
            return
        }

        val lineRange = calculateLineRange(editor)
        val output = if (lineRange.startsWith("#L")) {
            // Multi-line selection: prepend @ to the entire path
            "@$relativePath$lineRange"
        } else {
            // Single-line or no selection
            "$relativePath$lineRange"
        }

        copyToClipboard(output)
        showNotification(project, "Copied: $output")
    }

    override fun update(e: AnActionEvent) {
        // Only enable when editor has focus
        e.presentation.isEnabled = e.getData(CommonDataKeys.EDITOR) != null
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

    private fun getRelativePath(project: Project, virtualFile: VirtualFile): String? {
        val projectBasePath = project.basePath ?: return null
        val filePath = virtualFile.path

        return try {
            val projectPath = Path.of(projectBasePath)
            val filePathObj = Path.of(filePath)
            filePathObj.relativeTo(projectPath).toString().replace('\\', '/')
        } catch (e: Exception) {
            null
        }
    }

    private fun calculateLineRange(editor: Editor): String {
        val selectionModel = editor.selectionModel
        val document = editor.document

        // Get the current cursor line (0-based from document, convert to 1-based)
        val cursorLine = document.getLineNumber(editor.caretModel.offset) + 1

        // Check if there's an actual selection
        if (!selectionModel.hasSelection()) {
            // No selection - return just the cursor line
            return ":$cursorLine"
        }

        // Get line numbers (convert to 1-based)
        val startLine = document.getLineNumber(selectionModel.selectionStart) + 1
        val endLine = document.getLineNumber(selectionModel.selectionEnd) + 1

        // If selection is on a single line
        return if (startLine == endLine) {
            ":$startLine" // IDE-compatible format
        } else {
            "#L$startLine-$endLine" // Claude Code format for ranges
        }
    }

    private fun copyToClipboard(text: String) {
        CopyPasteManager.getInstance().setContents(StringSelection(text))
    }

    private fun showNotification(project: Project, message: String) {
        Notifications.Bus.notify(
            Notification(
                "R3BL Copy Selection",
                "Copy Selection Path",
                message,
                NotificationType.INFORMATION
            ),
            project
        )
    }

    private fun showError(project: Project, message: String) {
        Notifications.Bus.notify(
            Notification(
                "R3BL Copy Selection",
                "Copy Selection Path Error",
                message,
                NotificationType.ERROR
            ),
            project
        )
    }
}
