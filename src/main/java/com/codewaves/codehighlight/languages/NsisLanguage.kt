package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = NSIS
Description = Nullsoft Scriptable Install System
Author = Jan T. Sott <jan.sott@gmail.com>
Website = http://github.com/idleberg
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class NsisLanguage : LanguageBuilder {
    val CONSTANTS = Mode(
        className = "variable",

        begin =
            """\${'$'}(ADMINTOOLS|APPDATA|CDBURN_AREA|CMDLINE|COMMONFILES32|COMMONFILES64|COMMONFILES|COOKIES|DESKTOP|DOCUMENTS|EXEDIR|EXEFILE|EXEPATH|FAVORITES|FONTS|HISTORY|HWNDPARENT|INSTDIR|INTERNET_CACHE|LANGUAGE|LOCALAPPDATA|MUSIC|NETHOOD|OUTDIR|PICTURES|PLUGINSDIR|PRINTHOOD|PROFILE|PROGRAMFILES32|PROGRAMFILES64|PROGRAMFILES|QUICKLAUNCH|RECENT|RESOURCES_LOCALIZED|RESOURCES|SENDTO|SMPROGRAMS|SMSTARTUP|STARTMENU|SYSDIR|TEMP|TEMPLATES|VIDEOS|WINDIR)"""
    )

    val DEFINES = Mode(
        // ${defines)
        className = "variable",

        begin =
            """\${'$'}+{[\w\.:-]+}"""
    )

    val VARIABLES = Mode(
        // $variables
        className = "variable",

        begin =
            """\${'$'}+\w+""",

        illegal =
            """\(\){}"""
    )

    val LANGUAGES = Mode(
        // $(language_strings)
        className = "variable",

        begin =
            """\${'$'}+\(listOf(\w\^\.:-]+\)"""
    )

    val PARAMETERS = Mode(
        // command parameters
        className = "params",

        begin = "(ARCHIVE|FILE_ATTRIBUTE_ARCHIVE|FILE_ATTRIBUTE_NORMAL|FILE_ATTRIBUTE_OFFLINE|FILE_ATTRIBUTE_READONLY|FILE_ATTRIBUTE_SYSTEM|FILE_ATTRIBUTE_TEMPORARY|HKCR|HKCU|HKDD|HKEY_CLASSES_ROOT|HKEY_CURRENT_CONFIG|HKEY_CURRENT_USER|HKEY_DYN_DATA|HKEY_LOCAL_MACHINE|HKEY_PERFORMANCE_DATA|HKEY_USERS|HKLM|HKPD|HKU|IDABORT|IDCANCEL|IDIGNORE|IDNO|IDOK|IDRETRY|IDYES|MB_ABORTRETRYIGNORE|MB_DEFBUTTON1|MB_DEFBUTTON2|MB_DEFBUTTON3|MB_DEFBUTTON4|MB_ICONEXCLAMATION|MB_ICONINFORMATION|MB_ICONQUESTION|MB_ICONSTOP|MB_OK|MB_OKCANCEL|MB_RETRYCANCEL|MB_RIGHT|MB_RTLREADING|MB_SETFOREGROUND|MB_TOPMOST|MB_USERICON|MB_YESNO|NORMAL|OFFLINE|READONLY|SHCTX|SHELL_CONTEXT|SYSTEM|TEMPORARY)"
    )

    val COMPILER = Mode(
        // !compiler_flags
        className = "keyword",

        begin =
            """\!(addincludedir|addplugindir|appendfile|cd|define|delfile|echo|else|endif|error|execute|finalize|getdllversion|gettlbversion|if|ifdef|ifmacrodef|ifmacrondef|ifndef|include|insertmacro|macro|macroend|makensis|packhdr|searchparse|searchreplace|system|tempfile|undef|verbose|warning)"""
    )

    val METACHARS = Mode(
        // $\n, $\r, $\t, $$
        className = "meta",

        begin =
            """\${'$'}(\\[nrt]|\${'$'})"""
    )

    val PLUGINS = Mode(
        // plug::ins
        className = "class",

        begin =
            """\w+::\w+"""
    )

    val STRING = Mode(
        className = "string",

        variants = listOf(
            Mode(
                begin = "\"",

                end = "\""
            ),
            Mode(
                begin = "\'",

                end = "\'"
            ),
            Mode(
                begin = "`",

                end = "`"
            )
        ),
        illegal =
            """\n""",

        contains = listOf(
            METACHARS,
            CONSTANTS,
            DEFINES,
            VARIABLES,
            LANGUAGES
        )
    )

    override fun build() = Mode(
        case_insensitive = false,
        keywords = listOf(
            Keyword(
                className = "keyword",

                value = "Abort AddBrandingImage AddSize AllowRootDirInstall AllowSkipFiles AutoCloseWindow BGFont BGGradient BrandingText BringToFront Call CallInstDLL Caption ChangeUI CheckBitmap ClearErrors CompletedText ComponentText CopyFiles CRCCheck CreateDirectory CreateFont CreateShortCut Delete DeleteINISec DeleteINIStr DeleteRegKey DeleteRegValue DetailPrint DetailsButtonText DirText DirVar DirVerify EnableWindow EnumRegKey EnumRegValue Exch Exec ExecShell ExecShellWait ExecWait ExpandEnvStrings File FileBufSize FileClose FileErrorText FileOpen FileRead FileReadByte FileReadUTF16LE FileReadWord FileSeek FileWrite FileWriteByte FileWriteUTF16LE FileWriteWord FindClose FindFirst FindNext FindWindow FlushINI FunctionEnd GetCurInstType GetCurrentAddress GetDlgItem GetDLLVersion GetDLLVersionLocal GetErrorLevel GetFileTime GetFileTimeLocal GetFullPathName GetFunctionAddress GetInstDirError GetLabelAddress GetTempFileName Goto HideWindow Icon IfAbort IfErrors IfFileExists IfRebootFlag IfSilent InitPluginsDir InstallButtonText InstallColors InstallDir InstallDirRegKey InstProgressFlags InstType InstTypeGetText InstTypeSetText Int64Cmp Int64CmpU Int64Fmt IntCmp IntCmpU IntFmt IntOp IntPtrCmp IntPtrCmpU IntPtrOp IsWindow LangString LicenseBkColor LicenseData LicenseForceSelection LicenseLangString LicenseText LoadLanguageFile LockWindow LogSet LogText ManifestDPIAware ManifestSupportedOS MessageBox MiscButtonText Name Nop OutFile Page PageCallbacks PageExEnd Pop Push Quit ReadEnvStr ReadINIStr ReadRegDWORD ReadRegStr Reboot RegDLL Rename RequestExecutionLevel ReserveFile Return RMDir SearchPath SectionEnd SectionGetFlags SectionGetInstTypes SectionGetSize SectionGetText SectionGroupEnd SectionIn SectionSetFlags SectionSetInstTypes SectionSetSize SectionSetText SendMessage SetAutoClose SetBrandingImage SetCompress SetCompressor SetCompressorDictSize SetCtlColors SetCurInstType SetDatablockOptimize SetDateSave SetDetailsPrint SetDetailsView SetErrorLevel SetErrors SetFileAttributes SetFont SetOutPath SetOverwrite SetRebootFlag SetRegView SetShellVarContext SetSilent ShowInstDetails ShowUninstDetails ShowWindow SilentInstall SilentUnInstall Sleep SpaceTexts StrCmp StrCmpS StrCpy StrLen SubCaption Unicode UninstallButtonText UninstallCaption UninstallIcon UninstallSubCaption UninstallText UninstPage UnRegDLL Var VIAddVersionKey VIFileVersion VIProductVersion WindowIcon WriteINIStr WriteRegBin WriteRegDWORD WriteRegExpandStr WriteRegMultiStr WriteRegNone WriteRegStr WriteUninstaller XPStyle"
            ),
            Keyword(
                className = "literal",

                value = "admin all auto both bottom bzip2 colored components current custom directory false force hide highest ifdiff ifnewer instfiles lastused leave left license listonly lzma nevershow none normal notset off on open print right show silent silentlog smooth textonly top true try un.components un.custom un.directory un.instfiles un.license uninstConfirm user Win10 Win7 Win8 WinVista zlib"
            )
        ),
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            hljs.COMMENT(
                ";",

                "\$",

                Mode(
                    relevance = 0
                )
            ),
            Mode(
                className = "function",

                beginKeywords = keywords("Function PageEx Section SectionGroup"),
                end = "\$"
            ),
            STRING,
            COMPILER,
            DEFINES,
            VARIABLES,
            LANGUAGES,
            PARAMETERS,
            PLUGINS,
            hljs.NUMBER_MODE
        )
    )
}
