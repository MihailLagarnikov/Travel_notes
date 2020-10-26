import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.core.ui.base.ProgressViewModel
import com.twosmalpixels.travel_notes.core.ui.base.ToolbarViewModel
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenseAllViewModel
import org.koin.java.standalone.KoinJavaComponent

abstract class BaseFragment : Fragment() {

    private lateinit var progressBar: ConstraintLayout
    private lateinit var content: View
    protected lateinit var progressViewModel: ProgressViewModel
    protected lateinit var expenseAllViewModel: ExpenseAllViewModel
    protected val oflineModeUseCase by KoinJavaComponent.inject(
        IOflineModeUseCase::class.java
    )


    abstract fun getToolbarParam(): ToolbarParam
    abstract fun getLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseAllViewModel =
            ViewModelProviders.of(requireActivity()).get("a", ExpenseAllViewModel::class.java)
        progressBar = view.findViewById(R.id.progress)
        content = view.findViewById(R.id.content)
        val toolbarViewModel =
            ViewModelProviders.of(requireActivity()).get(ToolbarViewModel::class.java)
        toolbarViewModel.toolbarParam.value = getToolbarParam()
        createProgressObserver()
    }

    private fun createProgressObserver() {
        progressViewModel =
            ViewModelProviders.of(requireActivity()).get(ProgressViewModel::class.java)
        progressViewModel.showProgress.observe(this, Observer { isShow ->
            progressBar.setVisibility(isShow)
            content.setVisibility(!isShow)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

}